package com.example.bank;

import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.UUID;

public class Account {

    public static final String TYPE_SAVINGS = "savings";
    public static final String TYPE_CASH = "cash";

    private final UUID accountNumber;
    private String name;
    private String type;
    private String pin;
    private double balance;
    private double overDraft;

    public Account(String name, String type) {
        this.name = name;
        this.type = type;

        if (type.equals(TYPE_SAVINGS)) {
            this.overDraft = 500.00;
        } else {
            this.overDraft = 1000.00;
        }

        this.balance = 100;
        this.accountNumber = UUID.randomUUID();
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void verify(int[] pin) {
        // business logic for pin verification
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", overDraft=" + overDraft +
                '}';
    }
}
