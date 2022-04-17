package com.example.demo;

import com.github.javafaker.Faker;
import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = init(new Faker(), 10);

//        System.out.println(accounts); // prints the array list object by invoking the toString

        // imperative
//        for (Account account : accounts) {
//            System.out.println(account);
//        }

        // imperative
//        for (int index = 0; index < accounts.size(); index++) {
//            System.out.println(accounts.get(index));
//        }

        // functional
        System.out.println("ALL ACCOUNTS");
        accounts.forEach(System.out::println);

        // filter in an imperative way
//        List<Account> chequingAccounts = new ArrayList<>();
//        for (Account account : accounts) {
//            if (account.getAccountType() == AccountType.CHEQUING) {
//                chequingAccounts.add(account);
//            }
//        }

//        System.out.println();
//        chequingAccounts.forEach(System.out::println);

        // filter in a functional way
        List<Account> filtered = accounts.stream()
                .filter(account -> account.getAccountType().equals(AccountType.CHEQUING))
                .collect(Collectors.toList());

        System.out.println("FILTERED ACCOUNTS -> CHEQUING");
        filtered.forEach(System.out::println);

        // sort in a functional way
        List<Account> sorted = accounts.stream()
                .sorted(Comparator.comparing(Account::getAccountType))
                .collect(Collectors.toList());

        System.out.println("SORTED ACCOUNTS -> ACCOUNT TYPE");
        sorted.forEach(System.out::println);

        List<Account> sortedReversed = accounts.stream()
                .sorted(Comparator.comparing(Account::getAccountType).reversed())
                .toList();

        System.out.println("SORTED ACCOUNTS REVERSED -> ACCOUNT TYPE");
        sortedReversed.forEach(System.out::println);

        List<Account> sortedMultipleCriteria = accounts.stream().sorted(Comparator.comparing(Account::getAccountType).thenComparing(Account::getBalance)).collect(Collectors.toList());
        System.out.println("SORTED ACCOUNTS MULTIPLE CRITERIA -> ACCOUNT TYPE & BALANCE");
        sortedMultipleCriteria.forEach(System.out::println);

//        Optional<Account> max = accounts.stream()
//                .max(Comparator.comparing(Account::getBalance));
//        System.out.println(max);

        // MAXIMUM BALANCE OF ALL ACCOUNTS
        System.out.println("MAX VALUE -> BALANCE");
        accounts.stream()
                .max(Comparator.comparing(Account::getBalance))
                .ifPresent(System.out::println);

        // Minimum BALANCE OF ALL ACCOUNTS
        System.out.println("MIN VALUE -> BALANCE");
        accounts.stream()
                .min(Comparator.comparing(Account::getBalance))
                .ifPresent(System.out::println);

        // ALL MATCH
        System.out.println("ALL MATCH");
        boolean allMatch = accounts.stream().allMatch(
                account -> account.getBalance().compareTo(BigDecimal.valueOf(3000L)) > 0);
        System.out.println(allMatch);

        // ANY MATCH
        System.out.println("ANY MATCH");
        boolean anyMatch = accounts.stream().anyMatch(
                account -> account.getBalance().compareTo(BigDecimal.valueOf(3000L)) > 0);
        System.out.println(anyMatch);

        // NONE MATCH
        System.out.println("NONE MATCH");
        boolean noneMatch = accounts.stream().noneMatch(
                account -> account.getBalance().compareTo(BigDecimal.valueOf(6000L)) > 0);
        System.out.println(noneMatch);

        // GROUPING
        Map<AccountType, List<Account>> grouping = accounts.stream()
                .collect(Collectors.groupingBy(Account::getAccountType));
//        System.out.println(grouping);

        System.out.println("ACCOUNTS GROUPED -> ACCOUNT TYPE");
        grouping.forEach((accountType, accountsList) -> {
            System.out.println(accountType);
            accountsList.forEach(System.out::println);
            System.out.println();
        });

        // MULTIPLE FUNCTIONAL METHODS CHAINED TOGETHER
        Optional<String> accountWithHighestBalance = accounts.stream()
                .filter(account -> account.getAccountType().equals(AccountType.SAVINGS))
                .max(Comparator.comparing(Account::getBalance))
                .map(Account::getName);

        System.out.println("ACCOUNT WITH HIGHEST BALANCE");
        accountWithHighestBalance.ifPresent(System.out::println);
    }

    private static List<Account> init(Faker faker, int minimumAccounts) {
        List<Account> bankAccounts = new ArrayList<>();

        for (int counter = 1; counter <= minimumAccounts; counter++) {
            bankAccounts.add(
                    Account.builder()
                            .name(faker.name().firstName())
                            .accountNumber(faker.idNumber().valid())
                            .balance(BigDecimal.valueOf(
                                    faker.number().numberBetween(1000, 5000)))
                            .accountType(AccountType.CHEQUING)
                            .build()
            );

            if (counter <= 5) {
                bankAccounts.add(
                        Account.builder()
                                .name(faker.name().firstName())
                                .accountNumber(faker.idNumber().valid())
                                .balance(BigDecimal.valueOf(
                                        faker.number().numberBetween(1000, 5000)))
                                .accountType(AccountType.SAVINGS)
                                .build()
                );
            }
        }

        return bankAccounts;
    }
}
