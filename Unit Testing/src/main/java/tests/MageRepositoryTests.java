// Resources
// https://www.baeldung.com/introduction-to-assertj
// https://joel-costigliola.github.io/assertj/core-8/api/org/assertj/core/api/AbstractThrowableAssert.html
// https://www.baeldung.com/junit-assert-exception

package tests;

import org.example.Mage;
import org.example.repository.MageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MageRepositoryTests {
    private MageRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new MageRepository();
    }

    @Test
    void getExistingMageTest() {
        Mage expectedMage = new Mage("MageA", 0, 0.0f);
        repository.addMage("MageA");

        Optional<Mage> foundMage = repository.getMage("MageA");

        assertThat(foundMage).isPresent();
        assertThat(foundMage.get().getName()).isEqualTo("MageA");
    }

    @Test
    void getNonExistingMageTest() {
        Optional<Mage> foundMage = repository.getMage("MageB");

        assertThat(foundMage).isEmpty();
    }

    @Test
    void deleteExistingMageTest() {
        repository.addMage("MageC");

        repository.deleteMage("MageC");

        assertThat(repository.getMage("MageC")).isEmpty(); // Check if the Mage was deleted
    }

    @Test
    void deleteNonExistingMageTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> repository.deleteMage("MageD"), "You're trying to delete an object that doesn't exist");
        assertThat(exception).hasNoCause().hasMessageEndingWith("You're trying to delete an object that doesn't exist");
    }

    @Test
    void addExistingMageTest() {
        repository.addMage("MageE");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> repository.addMage("MageE"), "You're trying to add an existing Mage");
        assertThat(exception).hasNoCause().hasMessageEndingWith("You're trying to add an existing Mage");
    }

    @Test
    void addNewMageTest() {
        repository.addMage("MageE");
        assertThat(repository.getMage("MageE")).isPresent();
    }

}
