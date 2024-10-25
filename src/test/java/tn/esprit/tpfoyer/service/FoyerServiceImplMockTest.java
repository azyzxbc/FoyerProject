package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Use MockitoExtension for JUnit 5
class FoyerServiceImplMockTest {

    @Mock
    private FoyerRepository foyerRepository; // Mock the FoyerRepository

    @InjectMocks
    private FoyerServiceImpl foyerService; // Inject mocks into the service

    @BeforeEach
    public void setUp() {
        // No explicit setup needed; Mockito handles the initialization
    }

    @Test
    void testRetrieveAllFoyers() {
        // Mock data
        Foyer foyer1 = new Foyer();
        Foyer foyer2 = new Foyer();
        List<Foyer> mockFoyers = Arrays.asList(foyer1, foyer2);

        when(foyerRepository.findAll()).thenReturn(mockFoyers); // Mocking repository call

        // Test
        List<Foyer> foyers = foyerService.retrieveAllFoyers();

        // Verify
        assertEquals(2, foyers.size());
        verify(foyerRepository).findAll(); // Verify the interaction with the repository
    }

    @Test
    void testRetrieveFoyer() {
        // Mock data
        Long foyerId = 1L;
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(mockFoyer)); // Mocking repository call

        // Test
        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        // Verify
        assertNotNull(retrievedFoyer);
        verify(foyerRepository).findById(foyerId); // Verify the interaction with the repository
    }

    @Test
    void testAddFoyer() {
        // Mock data
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer); // Mocking repository call

        // Test
        Foyer addedFoyer = foyerService.addFoyer(mockFoyer);

        // Verify
        assertNotNull(addedFoyer);
        verify(foyerRepository).save(mockFoyer); // Verify the interaction with the repository
    }

    @Test
    void testModifyFoyer() {
        // Mock data
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer); // Mocking repository call

        // Test
        Foyer modifiedFoyer = foyerService.modifyFoyer(mockFoyer);

        // Verify
        assertNotNull(modifiedFoyer);
        verify(foyerRepository).save(mockFoyer); // Verify the interaction with the repository
    }

    @Test
    void testRemoveFoyer() {
        // Mock data
        Long foyerId = 1L;

        // Test
        foyerService.removeFoyer(foyerId);

        // Verify that deleteById method is called with the correct ID
        verify(foyerRepository).deleteById(foyerId); // Verify the interaction with the repository
    }
}

