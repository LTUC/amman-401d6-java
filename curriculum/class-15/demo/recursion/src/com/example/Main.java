package com.example;

public class Main {
    public static void main(String[] args) {
        // problem is adding 3 numbers

        int result = 0;

        for (int counter = 1; counter <= 3; counter++) {
            result = result + counter;
        }

//        System.out.println(result);

//        recurTestFunc(1);

            System.out.println("the total is => " + getTotal(3));
    }

    private static int recurTestFunc(int counter) {
        // base case
        if (counter == 3) {
            return -1;
        }

        int x = 0;
        String name = "Class";

        System.out.println("The function was called");
        return recurTestFunc(++counter);
    }

    public static int getTotal(int number) {
        //check whether the number not equals to zero
        if (number != 0) {
            //recursively calling the function
            return number + getTotal(number - 1);
        }
        //return the total when the number becomes zero
        else {
            return number;
        }
    }
}
