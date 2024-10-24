package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class FoyerServiceImplMockTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer = new Foyer();

    private List<Foyer> listFoyers = new ArrayList<Foyer>() {
        {
            add(new Foyer());
            add(new Foyer());
        }
    };

    @Test
    public void testRetrieveAllFoyers() {
        // Mocking the repository layer
        Mockito.when(foyerRepository.findAll()).thenReturn(listFoyers);

        // Calling the service method
        List<Foyer> retrievedFoyers = foyerService.retrieveAllFoyers();

        // Verifying the result
        Assertions.assertNotNull(retrievedFoyers);
        Assertions.assertEquals(2, retrievedFoyers.size());
    }

    @Test
    public void testRetrieveFoyerById() {
        // Mocking the repository layer
        Mockito.when(foyerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(foyer));

        // Calling the service method
        Foyer retrievedFoyer = foyerService.retrieveFoyer(1L);

        // Verifying the result
        Assertions.assertNotNull(retrievedFoyer);
    }

    @Test
    public void testAddFoyer() {
        // Mocking the repository layer
        Mockito.when(foyerRepository.save(Mockito.any(Foyer.class))).thenReturn(foyer);

        // Calling the service method
        Foyer addedFoyer = foyerService.addFoyer(foyer);

        // Verifying the result
        Assertions.assertNotNull(addedFoyer);
    }

    @Test
    public void testModifyFoyer() {
        // Mocking the repository layer
        Mockito.when(foyerRepository.save(Mockito.any(Foyer.class))).thenReturn(foyer);

        // Modifying the foyer object
        foyer.setName("Updated Name");

        // Calling the service method
        Foyer modifiedFoyer = foyerService.modifyFoyer(foyer);

        // Verifying the result
        Assertions.assertNotNull(modifiedFoyer);
        Assertions.assertEquals("Updated Name", modifiedFoyer.getName());
    }

    @Test
    public void testRemoveFoyer() {
        long foyerId = 1L;

        // Ensure you're calling doNothing on the mock repository
        Mockito.doNothing().when(foyerRepository).deleteById(foyerId);

        // Calling the service method
        foyerService.removeFoyer(foyerId);

        // Verify that deleteById was called once with the expected argument
        Mockito.verify(foyerRepository, Mockito.times(1)).deleteById(foyerId);
    }
}
