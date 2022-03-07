package com.company.mansys;

import com.company.mansys.behaviors.Coder;

public abstract class Programmer extends Employee implements Coder {
    private final String degree;

    public Programmer(String name, String position, Long lastToBeHired, String degree) {
        super(name, position, lastToBeHired);
        this.degree = degree;
    }

    @Override
    public void work() {
        System.out.println("I code Java all day long");
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public void test() {

    }

    @Override
    public void writeCode() {

    }
}
