package com.example;

import com.example.demo.ExampleThree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

//        ExampleOne exampleOne = new ExampleOne();
//        exampleOne.mapDemo();
//
//        ExampleTwo exampleTwo = new ExampleTwo();
//        exampleTwo.setDemo();

        ExampleThree exampleThree = new ExampleThree();
//        try {
//            exampleThree.consoleInputOldWay();
//        } catch (IOException exception) {
//            System.err.println(exception.getMessage());
//        }

//        exampleThree.consoleInputNewWay();

//        exampleThree.fileDemoReader();
        exampleThree.fileDemoReaderResourceFolder();

//        exampleThree.fileDemoReader("class-names");
//        exampleThree.createFileDemo("class-ages");

//        exampleThree.fileWriter(exampleThree.createFileDemo("class-names"));

//        System.out.println(UtilClass.capitalize("jason"));
    }
}
