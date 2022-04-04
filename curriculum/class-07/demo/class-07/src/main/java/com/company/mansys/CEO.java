package com.company.mansys;

import com.company.mansys.behaviors.Fire;
import com.company.mansys.behaviors.Hire;
import com.company.mansys.behaviors.Promote;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

public class CEO extends Employee implements Fire, Hire, Promote {

    private boolean hasCompanyCar;
    private boolean hasPrivateOffice;

    public CEO(String name, String position, Long lastToBeHired) {
        super(name, position, lastToBeHired);
    }

    @Override
    public void work() {
        System.out.println("I give orders all day");
        System.out.println("Attend meetings");
    }

    public void setHasCompanyCar(boolean hasCompanyCar) {
        this.hasCompanyCar = hasCompanyCar;
    }

    public void setHasPrivateOffice(boolean hasPrivateOffice) {
        this.hasPrivateOffice = hasPrivateOffice;
    }

    public boolean isHasCompanyCar() {
        return hasCompanyCar;
    }

    public boolean isHasPrivateOffice() {
        return hasPrivateOffice;
    }

    @Override
    public void fire(Employee employee) {
        System.out.println(employee.getName() + " You are fired");
    }

    private boolean interview(Employee employee) {
        System.out.println("What is 3 + 3 => " + employee.getName());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter answer");
        int answer = scanner.nextInt();
        int result = interviewQuestion(3, 3);

        return answer == (result);
    }

    @Override
    public void hire(Employee employee) {
        if (interview(employee)) {
            System.out.println("Welcome to the team");
        } else {
            System.out.println("We will get back to you");
        }
    }

    private int interviewQuestion(int numOne, int numTwo) {
        return numOne + numTwo;
    }

    @Override
    public void promote(Employee employee) {

    }

    @Override
    public void payIncrease(Employee employee) {

    }

    public void paySalary() {
        System.out.println("I pay all the salaries");
    }
}
