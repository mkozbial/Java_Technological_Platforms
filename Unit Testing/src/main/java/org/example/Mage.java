package org.example;

public class Mage {
    private final String name;
    private int level = 0;

    float power = 0;

    public Mage(String name) {
        this.name = name;
    }

    public Mage(String name, int level, float power) {
        this.name = name;
        this.level = level;
        this.power = power;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return ("Name: " + this.name + " Level: " + this.level + " Power: " + this.power);
    }
}
