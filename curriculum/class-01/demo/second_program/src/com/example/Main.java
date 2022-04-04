package com.example;

/*
This is the main class it contains the source code
 */
public class Main {

    // primitive types
    static double salary;
    int marbles = 78;
    short myHeight = 2;
    char initial = 'v';
    static long highWayLength;
    int hertz = 1_000_000;
    static boolean isHot;
    float sizeOfOcean = 78989.0f;

    // Reference Types
    String bankName = "sdjsdkjskjdj";

    /**
     * This is our main method
     * it is the starting point of the program
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        // call a method
        methodDemo(arguments);
        String name = userName(arguments, 23);
        System.out.println("The username is => " + name);

        salaryIncrease(20);

        System.out.println("is it hot => " + isHot);
        System.out.println("is it long => " + highWayLength);
    }

    /**
     * This is our first method
     *
     * @param args
     */
    public static void methodDemo(String[] args) {
        System.out.println("Hello World => " + args[0]);

        // If statement
        if (args[0].equals("Bob")) {
            System.out.println("Bob works at ASAC");
        } else {
            System.out.println("Where is Bob");
        }

        System.out.println("Bob is => " + args[1] + " years old");

        // for loop
//        System.out.println("1");
//        System.out.println("2");
//        System.out.println("3");
//        System.out.println("4");
//        System.out.println("5");

        for (int index = 0; index < args.length; index++) {
//            System.out.println("index => " + index);
            System.out.println("the argument at position => " + index + " is => " + args[index]);
        }

        // while loop
        int counter = 10; // local variable
        while (counter >= 1) {
            System.out.println("The counter is => " + counter);
            counter--; // aka counter = counter - 1;
        }

        String[] months = {"January", "February", "March"};
//        if (month.equals("February")) {
//          System.out.println("28 Days in => " + month);
//        } else if (month.equals("")) {
//
//        } else if (month.equals("")) {
//
//        } else if (month.equals("")) {
//
//        }

        for (String month : months) {
            switch (month) {
                case "January":
                    System.out.println("31 Days in => " + month);
                    break;
                case "February":
                    System.out.println("28 Days in => " + month);
                    break;
                default:
                    System.out.println("Days for this month are not known");
            }
        }
    }

    public static String userName(String[] args, int age) {
        System.out.println("Bob's age is => " + age);
        return args[0];
    }

    public static void salaryIncrease(double increase) {
        double percentage = increase / 100;
        salary = salary + (salary * percentage);
        salary *= 1 + percentage;
        System.out.println("My new salary is => " + salary);
    }
}
