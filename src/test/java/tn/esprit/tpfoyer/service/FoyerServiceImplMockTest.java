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

@ExtendWith(MockitoExtension.class)
class FoyerServiceImplMockTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    public void setUp() {
        // No explicit setup needed; Mockito handles the initialization
    }

    @Test
    void testRetrieveAllFoyers() {
        Foyer foyer1 = new Foyer();
        Foyer foyer2 = new Foyer();
        List<Foyer> mockFoyers = Arrays.asList(foyer1, foyer2);

        when(foyerRepository.findAll()).thenReturn(mockFoyers);

        List<Foyer> foyers = foyerService.retrieveAllFoyers();

        assertEquals(2, foyers.size());
        verify(foyerRepository).findAll();
    }

    @Test
    void testRetrieveFoyerPresent() {
        Long foyerId = 1L;
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(mockFoyer));

        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        assertNotNull(retrievedFoyer);
        assertEquals(mockFoyer, retrievedFoyer);
        verify(foyerRepository).findById(foyerId);
    }

    @Test
    void testRetrieveFoyerNotPresent() {
        Long foyerId = 1L;
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.empty());

        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        assertNull(retrievedFoyer);
        verify(foyerRepository).findById(foyerId);
    }

    @Test
    void testAddFoyer() {
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer);

        Foyer addedFoyer = foyerService.addFoyer(mockFoyer);

        assertNotNull(addedFoyer);
        verify(foyerRepository).save(mockFoyer);
    }

    @Test
    void testModifyFoyer() {
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer);

        Foyer modifiedFoyer = foyerService.modifyFoyer(mockFoyer);

        assertNotNull(modifiedFoyer);
        verify(foyerRepository).save(mockFoyer);
    }

    @Test
    void testRemoveFoyer() {
        Long foyerId = 1L;

        foyerService.removeFoyer(foyerId);

        verify(foyerRepository).deleteById(foyerId);
    }
}
