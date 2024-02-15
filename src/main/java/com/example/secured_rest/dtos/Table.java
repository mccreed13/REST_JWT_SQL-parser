package com.example.secured_rest.dtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Table {
    String name;
    List<Map<String, String>> records;
}
