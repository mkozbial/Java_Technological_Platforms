package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;

@Entity
public class Mage {
    @Id
    private String name;

    private int level;

    @ManyToOne
    private Tower tower;

    public Mage() {

    }

    public  void setName(String name) {
        this.name = name;
    }

    public  void setLevel(int level) {
        this.level = level;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public String getName() {
        return this.name;
    }


    public double getLevel() {
        return this.level;
    }

    public Tower getTower() {
        return this.tower;
    }
}
