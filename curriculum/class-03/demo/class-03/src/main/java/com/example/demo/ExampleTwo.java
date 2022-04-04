package com.example.demo;

import java.util.HashSet;
import java.util.Set;

/*
This is for sets
 */
public class ExampleTwo {

    public void setDemo() {
        System.out.println("< =================== Sets =====================> ");

        Set<String> carCompanies = new HashSet<>();
        carCompanies.add("Volvo");
        carCompanies.add("Toyota");
        carCompanies.add("Audi");
        carCompanies.add("Mercedes");
        carCompanies.add("Land Rover");

        carCompanies.remove("Toyota");
        carCompanies.add("Volvo");
        carCompanies.add(null);
        carCompanies.add(null);

        System.out.println(carCompanies);

        for (String carCompany :
                carCompanies) {
            System.out.println("The company is => " + carCompany);
        }

        String[] otherCompanies = {"Tesla", "Ford", "GM"};
        String[] conversion = carCompanies.toArray(otherCompanies);

        System.out.println(conversion);
    }
}
