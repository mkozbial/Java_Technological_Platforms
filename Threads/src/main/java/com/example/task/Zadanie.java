package com.example.task;

import java.util.LinkedList;
import java.util.Queue;

public class Zadanie {
    private Queue<Integer> listaZadan = new LinkedList<Integer>();

    public synchronized void dodajZadanie(Integer liczba) {
        listaZadan.add(liczba);
        notify();
    }

    public synchronized int pobierzZadanie() throws InterruptedException{
        while( listaZadan.isEmpty() ) {
            wait();
        }
        return listaZadan.poll();
    }

    public void wypiszZadania() {
        for (Integer elem : listaZadan) {
            System.out.print("Zadanie " + elem + "; ");
        }
        System.out.print("\n");
    }

}
