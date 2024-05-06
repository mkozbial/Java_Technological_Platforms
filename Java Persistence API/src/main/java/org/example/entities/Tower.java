package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;

    private double height;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mage> mages;

    public Tower() {
        this.mages = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public void addMage(Mage mage) {
        mages.add(mage);
    }

    public void removeMage(Mage mage) {
        mages.remove(mage);
    }

    public List<String> getMages() {
        List<String> names = new ArrayList<String>();
        for (Mage m : mages) {
            names.add(m.getName());
        }

        return names;
    }

}
