// Resources
// https://www.baeldung.com/mockito-series


package tests;

import org.example.Mage;
import org.example.controller.MageController;
import org.example.repository.MageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MageControllerTests {
    private MageRepository repository;
    private MageController controller;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(MageRepository.class);
        controller = new MageController(repository);
    }

    @Test
    public void getExistingMageTests() {
        Mage mage = new Mage("MageA");
        when(repository.getMage("MageA")).thenReturn(Optional.of(mage));
        String result = controller.getMage("MageA");
        assertThat(result).isEqualTo(Optional.of(mage).toString());
    }

    @Test
    public void getNonExistingMageTests() {
        when(repository.getMage("MageB")).thenReturn(Optional.empty());
        String result = controller.getMage("MageB");
        assertThat(result).isEqualTo("not found");
    }

    @Test
    public void deleteExistingMageTest() {
        doNothing().when(repository).deleteMage("MageC");
        String result = controller.deleteMage("MageC");
        assertThat(result).isEqualTo("done");
    }

    @Test
    public void deleteNonExistingMageTest() {
        doThrow(new IllegalArgumentException()).when(repository).deleteMage("MageD");
        String result = controller.deleteMage("MageD");
        assertThat(result).isEqualTo("not found");
    }

    @Test
    void addNewMageTest() {
        doNothing().when(repository).addMage("MageE");
        String result = controller.addMage("MageE");
        assertThat(result).isEqualTo("done");
    }

    @Test
    void addExistingMageTest() {
        doThrow(new IllegalArgumentException("You're trying to add an existing Mage")).when(repository).addMage("MageF");
        String result = controller.addMage("MageF");
        assertThat(result).isEqualTo("bad request");
    }
}
