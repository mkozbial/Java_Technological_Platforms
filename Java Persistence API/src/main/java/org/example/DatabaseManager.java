package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Mage;
import org.example.entities.Tower;

import java.util.List;

public class DatabaseManager {
    private EntityManagerFactory managerFactory;

    private EntityManager manager;

    public DatabaseManager() {
        managerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        manager = managerFactory.createEntityManager();
    }

    public void addMage(Mage mage) {
        manager.getTransaction().begin();
        manager.persist(mage);
        manager.getTransaction().commit();
    }

    public void removeMage(Mage mage) {
        manager.getTransaction().begin();
        manager.remove(mage);
        manager.getTransaction().commit();
        System.out.println("Mage " + mage.getName() + " removed");
    }

    public List<Mage> getMages() {
        return manager.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();
    }


    public void addTower(Tower tower) {
        manager.getTransaction().begin();
        manager.persist(tower);
        manager.getTransaction().commit();
        System.out.println("Dodano wieżę: " + tower.getName());
    }

    public void removeTower (Tower tower) {
        manager.getTransaction().begin();
        manager.remove(tower);
        manager.getTransaction().commit();
        System.out.println("Tower " + tower.getName() + " removed");
    }

    public List<Tower> getTowers() {
        return manager.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();
    }

    public void end() {
        manager.close();
        managerFactory.close();
    }
}
