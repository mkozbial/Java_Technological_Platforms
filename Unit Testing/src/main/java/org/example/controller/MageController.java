package org.example.controller;

import org.example.Mage;
import org.example.repository.MageRepository;

import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String deleteMage(String name) {
        try {
            repository.deleteMage(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String getMage(String name) {
        Optional<Mage> mage = repository.getMage(name);
        if(mage.isEmpty()) {
            return "not found";
        }
        return mage.toString();
    }

    // wywołaniem metody z serwisu z poprawnym parametrem  ???
    public String addMage(String name) {
        try {
            repository.addMage(name);
            return "done";
        }
        catch (IllegalArgumentException e) {
            return "bad request";
        }
    }
}
