class Main {

    public static void main(String[] arguments) {
        System.out.println("Hello World => " + arguments[0]);

        if (arguments[0].equals("Bob")) {
            System.out.println("Bob works at ASAC");
        } else {
            System.out.println("Where is Bob");
        }

        System.out.println("Bob is => " + arguments[1] + " years old");
    }
}
