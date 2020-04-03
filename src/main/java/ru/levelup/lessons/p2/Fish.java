package ru.levelup.lessons.p2;

public class Fish {
    public String name;
    private int weight;

    public Fish() {
        name = "Murena";
        weight = 2;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
