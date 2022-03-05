package animalResort;

public class HummingBird extends Animal implements FlyingAnimal {
    public HummingBird(String name, int age) {
        super(name, age);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void fly() {
        System.out.println("I fly 100 feet off the ground");
    }

    public void canEatNectar() {
        System.out.println("Its sweet");
    }
}
