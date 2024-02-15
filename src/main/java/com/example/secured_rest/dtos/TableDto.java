package com.example.secured_rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class TableDto {
    String table;
    List<Map<String, String>> records;
}
