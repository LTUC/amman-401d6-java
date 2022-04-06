package streamFun;

public class Java401Person implements Comparable<Java401Person>
{
    String name;
    boolean isTired;
    int numOfPets;
    int numOfEmptyFiveHourEnergiesCurrently;
    public String[] petNames;
    boolean isCurrentlyConfused;
    String hobby;
    boolean isSmart;

    public Java401Person(String name, boolean isTired, int numOfPets, int numOfEmptyFiveHourEnergiesCurrently, String[] petNames, boolean isCurrentlyConfused, String hobby, boolean isSmart)
    {
        this.name = name;
        this.isTired = isTired;
        this.numOfPets = numOfPets;
        this.numOfEmptyFiveHourEnergiesCurrently = numOfEmptyFiveHourEnergiesCurrently;
        this.petNames = petNames;
        this.isCurrentlyConfused = isCurrentlyConfused;
        this.hobby = hobby;
        this.isSmart = true;
    }

    @Override
    public int compareTo(Java401Person o)
    {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }
}
