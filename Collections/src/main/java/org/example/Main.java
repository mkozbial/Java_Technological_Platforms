package org.example;
import java.util.*;

import com.example.models.Obiekt;
import com.example.util.KomparatorDodatkoweKryterium;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String metodaSortowania = args[0];
            // "brak" - brak sortowania
            // "nat" - naturalne kryterium sortowania
            // "alt" - alternatywne kryterium sortowania

            Set<Obiekt> zbiórObiektów;
            Map<Obiekt, Integer> statystyka;

            if ("nat".equals(metodaSortowania)) {
                zbiórObiektów = new TreeSet<>();
                statystyka = new TreeMap<>();
            } else if ("alt".equals(metodaSortowania)) {
                zbiórObiektów = new TreeSet<>(new KomparatorDodatkoweKryterium());
                statystyka = new TreeMap<>();
            } else {
                zbiórObiektów = new HashSet<>();
                statystyka = new HashMap<>();
            }

            Obiekt rodzic1 = new Obiekt("Rodzic1_Z_2_Dzieci", 10000, 1000.00, metodaSortowania);
            zbiórObiektów.add(rodzic1);
            Obiekt dziecko1 = new Obiekt("Rodzic1_A", 15000, 2334.341, metodaSortowania);
            Obiekt dziecko2 = new Obiekt("Rodzic1_B", 100, 2954454.46, metodaSortowania);
            rodzic1.addChild(dziecko1);
            rodzic1.addChild(dziecko2);
            zbiórObiektów.add(dziecko1);
            zbiórObiektów.add(dziecko2);

            Obiekt rodzic2 = new Obiekt("Rodzic2_Z_3_Dzieci", 954, 3333.00, metodaSortowania);
            zbiórObiektów.add(rodzic2);
            Obiekt dziecko3 = new Obiekt("Rodzic2_A(z 2 swoich dzieci)", 420, 34234.240, metodaSortowania);
            rodzic2.addChild(dziecko3);
            zbiórObiektów.add(dziecko3);

            Obiekt potomek1 = new Obiekt("Rodzic2_A_4", 65, 18.734, metodaSortowania);
            Obiekt potomek2 = new Obiekt("Rodzic2_A_2", 654, 564.43, metodaSortowania);
            dziecko3.addChild(potomek1);
            dziecko3.addChild(potomek2);
            zbiórObiektów.add(potomek1);
            zbiórObiektów.add(potomek2);

            Obiekt dziecko4 = new Obiekt("Rodzic2_B", 2137, 654.46, metodaSortowania);
            Obiekt dziecko5 = new Obiekt("Rodzic2_C", 143, 184.99, metodaSortowania);
            rodzic2.addChild(dziecko4);
            rodzic2.addChild(dziecko5);
            zbiórObiektów.add(dziecko4);
            zbiórObiektów.add(dziecko5);

            Obiekt rodzic3 = new Obiekt("Rodzic3_Z_1_Dzieckiem", 45, 653423.00, metodaSortowania);
            zbiórObiektów.add(rodzic3);
            Obiekt dziecko6 = new Obiekt("Rodzic3_A", 784, 36523.01, metodaSortowania);
            rodzic3.addChild(dziecko6);
            zbiórObiektów.add(dziecko6);

            Obiekt rodzic4 = new Obiekt("Rodzic4_Z_3_Dzieci", 876, 9995.00, metodaSortowania);
            zbiórObiektów.add(rodzic4);
            Obiekt dziecko9 = new Obiekt("Rodzic4_A", 876, 234.345, metodaSortowania);
            Obiekt dziecko10 = new Obiekt("Rodzic4_B", 345, 345453.189, metodaSortowania);
            Obiekt dziecko11 = new Obiekt("Rodzic4_C", 689, 56434.99, metodaSortowania);
            rodzic4.addChild(dziecko9);
            rodzic4.addChild(dziecko10);
            rodzic4.addChild(dziecko11);
            zbiórObiektów.add(dziecko9);
            zbiórObiektów.add(dziecko10);
            zbiórObiektów.add(dziecko11);

            for (Obiekt obj : zbiórObiektów) {
                //wypisuje potomkow tylko node'ów bez rodziców, żeby nie było powtórzeń
                if(obj.getParent() == null)
                    wypisz(obj, 0);
            }

            statystyka = stworzStatystyke(zbiórObiektów);

            System.out.print("\n");
            System.out.println("Statystyka:");
            for (Map.Entry<Obiekt, Integer> entry : statystyka.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } else {
            System.out.println("Brak argumentów.");
        }
    }
    private static void wypisz(Obiekt obiekt, int zaglebienie) {
        for (int i = 0; i <= zaglebienie; i++) {
            System.out.print("-");
        }
        System.out.println(obiekt.toString());

        // Rekurencyjnie wypisz dzieci
        if (obiekt.getChildren() != null) {
            for (Obiekt child : obiekt.getChildren()) {
                wypisz(child, zaglebienie + 1);
            }
        }
    }

    private static Map<Obiekt, Integer> stworzStatystyke(Set<Obiekt> Obiekty) {
        Map<Obiekt, Integer> statystyki = new HashMap<>();

        for (Obiekt obiekt : Obiekty) {
            int licznik = policzPotomkow(obiekt);
            statystyki.put(obiekt, licznik);
        }

        return statystyki;
    }

    private static int policzPotomkow(Obiekt obiekt) {
        int licznik = 0;

        if (obiekt.getChildren() != null) {
            licznik += obiekt.getChildren().size();
            for (Obiekt child : obiekt.getChildren()) {
                licznik += policzPotomkow(child);
            }
        }

        return licznik;
    }
}