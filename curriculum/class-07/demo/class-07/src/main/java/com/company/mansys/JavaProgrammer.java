package com.company.mansys;

public class JavaProgrammer extends Programmer implements com.company.mansys.languages.JavaProgrammer {
    public JavaProgrammer(String name, String position, Long lastToBeHired, String degree) {
        super(name, position, lastToBeHired, degree);
    }

    @Override
    public void createAndroidApp() {
        System.out.println("I create android Apps");
    }
}
