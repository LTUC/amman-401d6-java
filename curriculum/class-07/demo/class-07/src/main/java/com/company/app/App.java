package com.company.app;

import com.company.mansys.*;
import com.company.mansys.languages.JavaProgrammer;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("<=============== Welcome to ManSYS ===============>");

        List<Employee> employees = new ArrayList<>();

        Employee jeff = new CEO("Jeff Bezos", "CEO", 1000L);
        Employee jason = new ProjectManager("Jason", "Project Manager", 1001L);
        Employee wael = new com.company.mansys.JavaProgrammer("Wael", "Developer", 1002L, "Comp Sci");
        Employee baraah = new JavascriptProgrammer("Baraah", "Developer", 1003L, "Soft Eng");

        employees.add(jeff);
        employees.add(jason);
        employees.add(wael);
        employees.add(baraah);

        JavaProgrammer hamza = new com.company.mansys.JavaProgrammer("hamza", "Senior", 1004L, "Comp Sci");

        CEO jeffCasted = (CEO) jeff;
        jeffCasted.hire(wael);

        Address address = new Address("123 No where Jordan Ave");
        Company softTech = new Company("Soft Tech Corp", address);

        for (Employee employee : employees) {
            softTech.addEmployee(employee);
        }
    }
}
