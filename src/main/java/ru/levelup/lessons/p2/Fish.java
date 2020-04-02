package ru.levelup.lessons.p2;

public class Fish {
    public String name;
    private int weight;

    public Fish() {
        name = "Murena";
        weight = 2;
    }
    public String toString(){
        return "Fish "+name+ " weight: "+weight;
    }
}
