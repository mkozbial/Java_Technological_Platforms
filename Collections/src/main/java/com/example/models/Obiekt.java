package com.example.models;

import com.example.util.KomparatorDodatkoweKryterium;

import java.util.*;

public class Obiekt implements Comparable<Obiekt> {
    private String imie;
    private int wartosc;
    private double waga;
    private String metodaSortowania;
    private Set<Obiekt> children;
    private Obiekt parent;

    //Konstruktor
    public Obiekt(String imie, int wartosc, double waga, String metodaSortowania) {
        this.imie = imie;
        this.wartosc = wartosc;
        this.waga = waga;
        this.metodaSortowania = metodaSortowania;
        if ("nat".equals(metodaSortowania)) {
            this.children = new TreeSet<>();
        } else if ("alt".equals(metodaSortowania)) {
            this.children = new TreeSet<>(new KomparatorDodatkoweKryterium());
        } else {
            this.children  = new HashSet<>();
        }
    }

    public void addChild(Obiekt child) {
        child.setParent(this);
        children.add(child);
    }

    public Obiekt getParent() {
        return parent;
    }

    public void setParent(Obiekt parent) {
        this.parent = parent;
    }

    public int getWartosc() {
        return wartosc;
    }

    public Set<Obiekt> getChildren() {
        return children;
    }

    public String getImie() {
        return imie;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        //Skoro nie należą do różnych klas to należą do tej samej klasy,
        //ale musimy zrobić rzutowanie, bo wchodzi nam obj klasy Object
        Obiekt objChanged = (Obiekt) obj;

        if (wartosc == objChanged.wartosc &&
            objChanged.waga == waga &&
            Objects.equals(imie, objChanged.imie)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, wartosc, waga);
    }

    @Override
    public int compareTo(Obiekt obj) {
        // Sortujemy po polu 'name'
        // Ujemna jeśli leksykograficznie mniejsze jest this.name
        // 0 jeśli są równe
        // Dodatnia jeśli leksykograficznie mniejsze jest object.name
        return this.imie.compareTo(obj.imie);
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + imie + '\'' +
                ", value=" + wartosc +
                ", price=" + waga +
                ", children=" + children +
                '}';
    }
}

