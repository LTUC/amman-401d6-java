package animalResort;

public class Sloth extends Animal implements ClimbingAnimal {
    public Sloth(String name, int age) {
        super(name, age);
    }

    public void fly() {
        System.out.println("I believe i can fly");
    }

    @Override
    public void eat(String food) {
        System.out.println("I am eating very slowly => " + food);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void sleep() {
        System.out.println("I am sleeping");
    }

    @Override
    public void useBinoculars() {
        System.out.println("I use advanced technology binoculars");
    }
}
