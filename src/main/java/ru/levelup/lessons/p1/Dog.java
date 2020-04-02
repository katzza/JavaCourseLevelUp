package ru.levelup.lessons.p1;

public class Dog {
    public String breed;
    private String name;
    public Colour color;

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
