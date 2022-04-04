package animalResort;

public class Snake extends Animal {

    private boolean isVenomous;

    public Snake(String name, int age, boolean isVenomous) {
        super(name, age);
        this.isVenomous = isVenomous;
    }

    public boolean isVenomous() {
        return isVenomous;
    }

    public void crawl() {
        System.out.println("I am crawling");
    }

    @Override
    public void reproduce() {

    }
}
