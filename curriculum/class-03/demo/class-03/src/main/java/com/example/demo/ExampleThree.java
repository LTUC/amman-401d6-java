package com.example.demo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/*
This is for I/O, input and output
 */
public class ExampleThree {

    // new way
    public void consoleInputNewWay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type something");
        String input = scanner.nextLine();
        System.out.println("Your input was => " + input + "\n\n");

        System.out.println("Type in your age");
        int age = scanner.nextInt();
        System.out.println("Your age is => " + age);

        scanner.close();
    }

    // old way
    public void consoleInputOldWay() throws IOException {
        System.out.println();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please type something");
        String input  = bufferedReader.readLine();

        int result = Integer.parseInt(input);

        System.out.println("Your input was => " + input);
        System.out.println("The result of the calculation => " + (result + 10));
    }

    public void fileDemoReader() {
        File file = new File("class-names.txt"); // will be relative to the root of the project

//        File file1 = new File("/home/jason/Desktop/class-names.txt"); // absolute paths

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println(fileNotFoundException.getMessage());
        }
    }

    public void fileDemoReader(String filename) {
        Path path = Paths.get(filename + ".txt");

        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);

                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        } finally {
            System.out.println("Program executed"); // read up on why this is useful
        }
    }

    public File createFileDemo(String filename) {
        File myFile = new File(filename + ".txt");
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created at => " + myFile.getAbsolutePath());

                return myFile;
            } else {
                System.out.println("File created already");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFile;
    }

    /*
    taken from
    https://docs.oracle.com/javase/tutorial/essential/io/file.html
     */
    public void fileWriter(File file) {
        Charset charset = StandardCharsets.US_ASCII;
        String s = "Jason Jones"; // TODO: 2/24/22 change this to accept input from the console
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getName()), charset)) {
            assert false;
            writer.write(s, 0, s.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public void fileDemoReaderResourceFolder() {
        File file = new File("src/main/resources/class-names.txt"); // will be relative to the root of the project

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println(fileNotFoundException.getMessage());
        }
    }
}
