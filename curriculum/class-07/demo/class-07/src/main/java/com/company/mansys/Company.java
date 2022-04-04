package com.company.mansys;

import java.util.List;

public class Company {

    private String name;

    // has one address and only one address, there are no branches
    private Address companyAddress;

    // has many employees
    private List<Employee> employees;

    public Company(String name, Address companyAddress) {
        this.name = name;
        this.companyAddress = companyAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
