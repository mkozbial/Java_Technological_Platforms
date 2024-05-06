package com.example.task;

import java.util.LinkedList;

public class Wyniki {
    private LinkedList<Boolean> wyniki = new LinkedList<Boolean>();

    public synchronized void dodajWynik(Boolean wynik) {
        wyniki.add(wynik);
    }

    public synchronized LinkedList<Boolean> pobierzWyniki() {
        return wyniki;
    }
}
