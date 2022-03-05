package animalResort;

public class Eagle extends Animal implements FlyingAnimal {
    public Eagle(String name, int age) {
        super(name, age);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void fly() {
        System.out.println("I fly at 10000");
    }

    public void canEatMeat() {
        System.out.println("Yum Yum");
    }
}
