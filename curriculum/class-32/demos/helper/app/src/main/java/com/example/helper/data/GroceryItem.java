package com.example.helper.data;

public class GroceryItem {

    private final String name;
    private final Integer cost;

    public GroceryItem(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }
}
