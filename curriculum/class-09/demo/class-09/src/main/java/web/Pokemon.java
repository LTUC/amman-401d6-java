package web;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private ArrayList<AbilityWrapper> abilities;

    public Pokemon(String name, ArrayList<AbilityWrapper> abilities) {
        this.name = name;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AbilityWrapper> getAbilities() {
        return abilities;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", abilities=" + abilities +
                '}';
    }
}
