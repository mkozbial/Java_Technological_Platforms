package org.example;
import com.example.task.WykonanieZadania;
import com.example.task.Wyniki;
import com.example.task.Zadanie;

import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Zadanie listaZadan = new Zadanie();
        Wyniki wyniki = new Wyniki();


        for(int i = 0; i < Integer.parseInt(args[0]); i++) {
            WykonanieZadania runnable = new WykonanieZadania(listaZadan, wyniki);
            Thread zadanie = new Thread(runnable);
            zadanie.start();
        }

        System.out.println("PODAJ LICZBĘ DO SPRAWDZENIA LUB SŁOWO 'zakoncz' ABY ZAKOŃCZYĆ PROGRAM");
        int liczba;
        Scanner scanner = new Scanner(System.in);
        String polecenie = scanner.next();
        while(!polecenie.equals("zakoncz")) {
            try {
                liczba = Integer.parseInt(polecenie);
            } catch (NumberFormatException e) {
                break;
            }

            listaZadan.dodajZadanie(liczba);

            polecenie = scanner.next();
        }

        scanner.close();

        Map<Thread, StackTraceElement[]> watki = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : watki.entrySet()) {
            Thread watek = entry.getKey();
            watek.interrupt();
        }

    }
}