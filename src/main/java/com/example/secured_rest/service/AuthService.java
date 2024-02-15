package com.example.secured_rest.service;

import com.example.secured_rest.dtos.JwtRequest;
import com.example.secured_rest.dtos.JwtResponse;
import com.example.secured_rest.dtos.RegistationUserDto;
import com.example.secured_rest.exceprtions.AppError;
import com.example.secured_rest.utils.JwtTokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> add(RegistationUserDto userDto){
        if(userService.findByUsername(userDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Username is already in use"), HttpStatus.BAD_REQUEST);
        }
        try {
            userService.createUser(userDto);
            return ResponseEntity.ok(userDto);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Role not found"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> auth(JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Uncorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
