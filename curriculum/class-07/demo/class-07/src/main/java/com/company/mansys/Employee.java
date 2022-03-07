package com.company.mansys;

import org.hashids.Hashids;

public abstract class Employee {

    public static final String CASH = "cash";
    public static final String CHEQUE = "cheque";
    public static final String WIRE_TRANSFER = "wire_transfer";

    private String employeeId;
    private String name;
    private String phone;
    private String email;
    private String position;

    private Double salary;

    public Employee(String name, String position, Long lastToBeHired) {
        this.name = name;
        this.position = position;

        Hashids hashids = new Hashids(this.name);
        employeeId = hashids.encode(lastToBeHired);
    }

    public abstract void work();

    public void collectSalary(String method) {
        System.out.println("Salary deductions");
        System.out.println("I want to be paid via => " + method);
        System.out.println("I have been paid");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
