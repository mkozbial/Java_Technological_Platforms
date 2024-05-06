package com.example.task;

import java.util.LinkedList;
import java.util.Queue;

public class WykonanieZadania implements Runnable{
    private Zadanie listaZadan;
    private Wyniki wyniki;

    private boolean zakoncz;

    public WykonanieZadania(Zadanie listaZadan, Wyniki wyniki) {
        this.listaZadan = listaZadan;
        this.wyniki = wyniki;
    }

    public boolean czyPierwsza(Integer liczba) {
        if(liczba == 1 || liczba == 0) {
            return false;
        }

        for(int i = 2; i <= liczba/2; i++)
        {
            if(( liczba % i) == 0) {
                return false;
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int zadanie = listaZadan.pobierzZadanie();
                System.out.println("Wykonuje zadanie " +  zadanie);
                boolean wynik = czyPierwsza(zadanie);
                System.out.println("Wynik zadania " +  zadanie + " to " + wynik);
                wyniki.dodajWynik(wynik);
            }
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany.");
        }
    }
}
