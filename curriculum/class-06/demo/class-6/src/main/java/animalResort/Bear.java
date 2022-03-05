package animalResort;

/*
Sub class
 */
public class Bear extends Animal implements ClimbingAnimal, FightingAnimal { // inherits from Animal super class

    private boolean canSwim;

    public Bear(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat(String food) {
        System.out.println("Tear food up and eat => " + food);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public void hibernate() {
        System.out.println("I am hibernating for the winter");
    }

    @Override
    public void useBinoculars() {
        System.out.println("I use 20X zoom binoculars");
    }

    @Override
    public void fights() {
        System.out.println("I once fought Kabib");
    }

    @Override
    public void wrestle() {
        System.out.println("I am better than Mike Tyson");
    }
}
