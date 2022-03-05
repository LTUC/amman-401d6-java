package app;

import animalResort.*;
import banking.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        animalResort();
        System.out.println("-------------------------- BANKING --------------------------");
        banking();
    }

    private static void birdSimulator(FlyingAnimal bird) {
        if (bird instanceof Eagle) {
            Eagle eagle = (Eagle) bird;
            eagle.canEatMeat();
        } else {
            System.out.println("You passed in a => " + bird.getClass().getSimpleName());
        }
    }

    private static void bearSimulator(Bear bear) {
        bear.eat("_______Salmon");
    }

    private static void slothSimulator(Sloth sloth) {
        sloth.eat("+++++++leaves");
    }

    private static void aardvarkSimulator(Aardvark aardvark) {
        aardvark.eat(">>>>>>>>ants");
    }

    private static void animalSimulator(Bear bear, Sloth sloth, Aardvark aardvark, Snake snake) {
        bear.eat("_______Salmon");
        sloth.eat("+++++++leaves");
        aardvark.eat(">>>>>>>>ants");
    }

    private static void betterSimulator(Animal animal) {
        // TODO: 3/3/22 convert this to use a swith statement
        if (animal instanceof Bear bear) {
            bear.hibernate();
        } else if (animal instanceof Sloth sloth) {
            sloth.sleep();
        }
    }

    public static void animalResort() {
        Snake snake = new Snake("Slither", 34, true);
        Bear yogi = new Bear("Yogi", 50);
        Bear mark = new Bear("Mark", 15);
        Sloth timmy = new Sloth("Timmy", 25);
        Aardvark john = new Aardvark("John", 12);

        System.out.println(yogi.isCanSwim());

        Animal animal;

        // this is polymorphism
        animal = new Sloth("Slowy", 78);

        // casting
        Sloth castedSloth = (Sloth) animal;
        System.out.println("++++++++++++++++++++");
        castedSloth.fly();

        System.out.println(yogi);
        System.out.println(timmy);
        System.out.println(john);

        yogi.eat("Salmon");
        john.eat("Ants");
        timmy.eat("Leaves");

        bearSimulator(yogi);
        slothSimulator(timmy);
        aardvarkSimulator(john);

        animalSimulator(yogi, timmy, john, snake);

        List<Animal> animals = new ArrayList<>();

        animals.add(yogi);
        animals.add(timmy);
        animals.add(john);
        animals.add(mark);
        animals.add(snake);

        for (Animal creature :
                animals) {
            betterSimulator(creature);
        }

        System.out.println("___________________________________________");
        FightingAnimal fightingAnimal = new Bear("McGregor", 69);
        fightingAnimal.fights();

        // this is polymorphism
        FlyingAnimal eagle = new Eagle("Bald Eagle", 100);
        FlyingAnimal hummingBird = new HummingBird("Dart", 23);

        eagle.fly();
        hummingBird.fly();

        birdSimulator(hummingBird);
    }

    public static void banking() {
        Member rawzi = new Member(
                "Rawzi",
                "AlHomran",
                "123 No-where Jordan",
                "555-555-5555");
        rawzi.openAccount(new AdultAccount(100));

        Member rawziSon = new Member(
                "Paul",
                "AlHomran",
                "123 No-where Jordan"
        );
        rawziSon.openAccount(new KiddyAccount());

        System.out.println("My balance is => " + rawzi.getAccount().getBalance());

        rawzi.getAccount().setBalance(300.50);

        System.out.println("My balance is => " + rawzi.getAccount().getBalance());

        Savings rawziSonSavings = new KiddyAccount(200);
        Savings rawziSavings = new AdultAccount(4500);

        rawziSon.setSavings(rawziSonSavings);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        rawziSavings.saveWithInterest(3450.67, Account.INTEREST);
        rawziSonSavings.saveWithInterest(2300.67, Account.INTEREST);
    }
}
