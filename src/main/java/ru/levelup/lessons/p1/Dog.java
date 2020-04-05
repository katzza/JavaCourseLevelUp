package ru.levelup.lessons.p1;

import reflection.homework.RandomInt;

public class Dog {
    public String breed;
   // @RandomInt(min = 1, max = 20)
    private String name;
    public Colour color;
    @RandomInt(min = 1, max = 20)
    public int age;

    public Dog() {
        this.breed = "retriever";
        this.name = "Lars";
        this.color = Colour.YELLOW;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", age=" + age +
                '}';
    }
}

enum Colour {
    BLACK,
    WHITE,
    RED,
    BROWN,
    YELLOW
}
