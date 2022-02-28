package com.example;

import com.example.animals.Duck;
import com.example.bank.Account;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
//        Duck mallardDuck = new Duck("Black", "Mallard", true);
//        Duck alabioDuck = new Duck("Blue", "Alabio", true);
//        System.out.println(mallardDuck);
//        System.out.println(alabioDuck);
//        System.out.println(mallardDuck);

        Account account = new Account("Jason Jones", Account.TYPE_SAVINGS);
        System.out.println("Account Information => " + account);

        System.out.println("Enter pin");
        Scanner scanner = new Scanner(System.in);

        String pin = scanner.nextLine();
        account.setPin(pin);

        System.out.println(account);


    }
}