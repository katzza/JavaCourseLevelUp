package ru.levelup.lessons.p1;

public class Cat {
    private String name;
    private Food food;
    public boolean isMouseHunter;

    public Cat() {
        this.name = "Mi";
        this.food = Food.MEAT;
        this.isMouseHunter = false;
    }

    public String toString() {
        return "Cat " + name + " eats " + food + " isMouseHunter: " + isMouseHunter;
    }
}

enum Food {
    VEGAN,
    MEAT,
    MILK
}
