package com.example.util;
import com.example.models.Obiekt;
import java.util.Comparator;

public class KomparatorDodatkoweKryterium implements Comparator<Obiekt> {

    @Override
    public int compare(Obiekt obj1, Obiekt obj2) {

        return Integer.compare(obj1.getWartosc(), obj2.getWartosc());

    }
}

