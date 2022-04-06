package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Account {
    private String name;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
}