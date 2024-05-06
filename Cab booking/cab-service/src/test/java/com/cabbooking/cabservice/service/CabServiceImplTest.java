package com.cabbooking.cabservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.cabservice.entity.Cab;
import com.cabbooking.cabservice.repository.CabRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CabServiceImplTest {

    @Mock
    private CabRepository cabRepository;

    @InjectMocks
    private CabServiceImpl cabService;

   

    @Test
    public void testInsertCab() {
        Cab cab = new Cab();
        when(cabRepository.save(any(Cab.class))).thenReturn(cab);

        Cab insertedCab = cabService.insertCab(cab);

        Assertions.assertNotNull(insertedCab);
        
    }

    @Test
    public void testUpdateCab() {
        Cab cab = new Cab();
        when(cabRepository.findById(anyInt())).thenReturn(Optional.of(cab));
        when(cabRepository.save(any(Cab.class))).thenReturn(cab);

        Cab updatedCab = cabService.updateCab(cab);

        Assertions.assertNotNull(updatedCab);
        
    }

    @Test
    public void testCountCabsOfType() {
        List<Cab> cabs = new ArrayList<>();
        
        when(cabRepository.findAll()).thenReturn(cabs);

        int count = cabService.countCabsOfType();

        Assertions.assertEquals(cabs.size(), count);
    }

    @Test
    public void testDeleteCab() {
        int cabId = 1; 
        Cab cab = new Cab();
        when(cabRepository.findById(cabId)).thenReturn(Optional.of(cab));

        cabService.deleteCab(cabId);

        verify(cabRepository, times(1)).delete(cab);
    }

    @Test
    public void testGetAllCabs() {
        List<Cab> cabs = new ArrayList<>();
        
        when(cabRepository.findAll()).thenReturn(cabs);

        List<Cab> retrievedCabs = cabService.getAllCabs();

        Assertions.assertEquals(cabs.size(), retrievedCabs.size());
        
    }

    @Test
    public void testGetCabById() {
        int cabId = 1; 
        Cab cab = new Cab();
        when(cabRepository.findById(cabId)).thenReturn(Optional.of(cab));

        Cab retrievedCab = cabService.getCabById(cabId);

        Assertions.assertNotNull(retrievedCab);
        
    }
}
