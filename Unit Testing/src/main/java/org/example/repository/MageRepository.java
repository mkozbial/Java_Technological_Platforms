// Resources
// https://www.baeldung.com/java-unit-testing-best-practices

package org.example.repository;

import org.example.Mage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Data access layer to objects of type Mage
// In unit testing, the repository can be dummy or simulated
// to isolate the logic under test from actual data access.
public class MageRepository {
    private final Map<String, Mage> mages;

    public MageRepository() {
        this.mages = new HashMap<>();
    }

    // Optional is a container object that may or may not contain a non-null value
    public Optional<Mage> getMage(String name) {
        return Optional.ofNullable(this.mages.getOrDefault(name, null));
    }

    public void deleteMage(String name) {
        if (!mages.containsKey(name)) {
            throw new IllegalArgumentException("You're trying to delete an object that doesn't exist");
        }
        this.mages.remove(name);
    }

    public void addMage(String name) {
        if (this.mages.containsKey(name)) {
            throw new IllegalArgumentException("You're trying to add an existing Mage");
        }
        this.mages.put(name, new Mage(name));
    }
}
