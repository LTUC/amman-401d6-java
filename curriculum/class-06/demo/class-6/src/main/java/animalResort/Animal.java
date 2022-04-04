package animalResort;

/*
Super Class
 */
public abstract class Animal {

    private String name;
    private int age;
    public double salary;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void eat(String food) {
        System.out.println("I am eating => " + food);
    }

    public abstract void reproduce();

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
