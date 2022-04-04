package animalResort;

public class Aardvark extends Animal {

    public Aardvark(String name, int age) {
        super(name, age); // calls the parent class constructor
    }

    @Override
    public void eat(String food) {
        System.out.println("Poke tongue out and eat => " + food);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void play() {
        System.out.println("I am playing music");
    }
}
