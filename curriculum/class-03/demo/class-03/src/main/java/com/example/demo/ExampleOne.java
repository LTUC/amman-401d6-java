package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/*
This is for maps
 */
public class ExampleOne {

    public void mapDemo() {
        System.out.println("< =================== Maps =====================> ");

//        String[] countries = {"China", "Russia", "Jordan", "Barbados"};
//        String[] capitals = {"Beijin", "Moscow", "Amman", "Bridgetown"};
//
//        for (int index = 0; index < countries.length; index++) {
//            System.out.println("Country => " + countries[index] + " Capital =>" + capitals[index]);
//        }

        Map<String, String> countryCapitals = new HashMap<>();
        countryCapitals.put("China", "Beijing");
        countryCapitals.put("Russia", "Moscow");
        countryCapitals.put("Jordan", "Amman");
        countryCapitals.put("England", "London");
        countryCapitals.put("Zootopia", "Charizard");
        countryCapitals.put("Xylophone", "Amman");
        countryCapitals.put("Barbados", "Bridgetown");


        System.out.println(countryCapitals.containsKey("England"));
        System.out.println(countryCapitals.containsValue("Bridgetown"));
        System.out.println(countryCapitals.get("Zootopia"));
        System.out.println(countryCapitals.putIfAbsent("England", "Tokyo"));

        HashMap<String, String> countryCapitalsCopy = (HashMap<String, String>) ((HashMap<String, String>) countryCapitals).clone();

        System.out.println("copy => " + countryCapitalsCopy);

        countryCapitalsCopy.clear();
        System.out.println(countryCapitalsCopy);
    }


}
