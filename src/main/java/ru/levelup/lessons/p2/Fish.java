package ru.levelup.lessons.p2;

import reflection.homework.RandomInt;

public class Fish {
    public String name;
    @RandomInt(min = 5, max = 30)
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
