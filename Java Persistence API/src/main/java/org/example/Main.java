// Resources
// https://www.geeksforgeeks.org/hibernate-example-using-jpa-and-mysql/
// https://www.baeldung.com/hibernate-entity-lifecycle
// https://www.baeldung.com/hibernate-entitymanager

package org.example;


import org.example.entities.Mage;
import org.example.entities.Tower;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseManager manager = new DatabaseManager();


        Mage mage1 = new Mage();
        mage1.setLevel(12);
        mage1.setName("MageA");

        manager.addMage(mage1);

        Mage mage2 = new Mage();
        mage2.setLevel(43);
        mage2.setName("MageB");
        manager.addMage(mage2);

        Mage mage3 = new Mage();
        mage3.setLevel(7);
        mage3.setName("MageC");
        manager.addMage(mage3);

        Mage mage4 = new Mage();
        mage4.setLevel(23);
        mage4.setName("MageD");
        manager.addMage(mage4);


        Tower tower = new Tower();
        tower.setName("A");
        tower.setHeight(200.0);
        manager.addTower(tower);

        tower.addMage(mage1);
        tower.addMage(mage2);
        tower.addMage(mage3);
        mage1.setTower(tower);
        mage2.setTower(tower);
        mage3.setTower(tower);

        Tower tower2 = new Tower();
        tower2.setName("B");
        tower2.setHeight(174.5);
        manager.addTower(tower2);

        tower2.addMage(mage4);
        mage4.setTower(tower2);


        List<Tower> towers = manager.getTowers();
        List<Mage> mages = manager.getMages();

        for(Tower i : towers) {
            System.out.println("Tower "  + i.getName() + " " + i.getMages());
        }

        for(Mage i : mages) {
            System.out.println("Mage " + i.getName() + " " + i.getLevel());
        }

        tower.removeMage(mage2);
        manager.removeMage(mage2);

        towers = manager.getTowers();
        mages = manager.getMages();

        for(Tower i : towers) {
            System.out.println("Tower "  + i.getName() + " " + i.getMages());
        }

        for(Mage i : mages) {
            System.out.println("Mage " + i.getName() + " " + i.getLevel());
        }

        manager.removeTower(tower);
        towers = manager.getTowers();
        mages = manager.getMages();

        for(Tower i : towers) {
            System.out.println("Tower "  + i.getName() + " " + i.getMages());
        }

        for(Mage i : mages) {
            System.out.println("Mage " + i.getName() + " " + i.getLevel());
        }

        manager.end();
    }
}
