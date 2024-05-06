package com.cabbooking.tripbookingservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cabbooking.tripbookingservice.entity.TripBooking;
import com.cabbooking.tripbookingservice.model.Customer;
import com.cabbooking.tripbookingservice.model.DriverResponse;
import com.cabbooking.tripbookingservice.model.TripResponse;
import com.cabbooking.tripbookingservice.repository.TripRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class TripBookingServiceImplTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private CustomerServiceConsumer customerService;

    @Mock
    private DriverServiceConsumer driverService;

    @InjectMocks
    private TripBookingServiceImpl tripBookingService;

    

    @Test
    public void testAddTrip() {
        TripBooking tripBooking = new TripBooking();
        when(customerService.fetchCustomerDetails(anyInt())).thenReturn(new Customer());
        when(driverService.findByAvailable()).thenReturn(new ArrayList<>());
        when(tripRepository.save(any(TripBooking.class))).thenReturn(tripBooking);

        TripBooking addedTrip = tripBookingService.AddTrip(tripBooking);

        Assertions.assertNotNull(addedTrip);
        
    }

    @Test
    public void testAllTrip() {
        List<TripBooking> trips = new ArrayList<>();
        
        when(tripRepository.findAll()).thenReturn(trips);

        List<TripResponse> tripResponses = tripBookingService.alltrip();

        Assertions.assertNotNull(tripResponses);
        
    }

    @Test
    public void testUpdateTrip() {
        TripBooking tripBooking = new TripBooking();
        when(tripRepository.findById(anyInt())).thenReturn(Optional.of(tripBooking));
        when(customerService.fetchCustomerDetails(anyInt())).thenReturn(new Customer());
        when(driverService.getDriverDetails(anyInt())).thenReturn(new DriverResponse());
        when(tripRepository.save(any(TripBooking.class))).thenReturn(tripBooking);

        TripBooking updatedTrip = tripBookingService.updateTrip(tripBooking);

        Assertions.assertNotNull(updatedTrip);
        
    }

    @Test
    public void testDeleteTrip() {
        int tripId = 1; 
        TripBooking tripBooking = new TripBooking();
        when(tripRepository.findById(tripId)).thenReturn(Optional.of(tripBooking));
        when(driverService.getDriverDetails(anyInt())).thenReturn(new DriverResponse());
        when(customerService.fetchCustomerDetails(anyInt())).thenReturn(new Customer());

        String result = tripBookingService.deleteTrip(tripId);

        Assertions.assertNotNull(result);
        
    }

    @Test
    public void testTripEnd() {
        int tripId = 1; 
        TripBooking tripBooking = new TripBooking();
        when(tripRepository.findById(tripId)).thenReturn(Optional.of(tripBooking));
        when(driverService.getDriverDetails(anyInt())).thenReturn(new DriverResponse());
        when(customerService.fetchCustomerDetails(anyInt())).thenReturn(new Customer());

        String result = tripBookingService.tripEnd(tripId);

        Assertions.assertNotNull(result);
        
    }

    @Test
    public void testGetTripHistoryByCustomer() {
        int customerId = 1; 
        List<TripBooking> trips = new ArrayList<>();
        
        when(tripRepository.findByCustomerId(customerId)).thenReturn(trips);
        when(driverService.getDriverDetails(anyInt())).thenReturn(new DriverResponse());
        when(customerService.fetchCustomerDetails(anyInt())).thenReturn(new Customer());

        List<TripResponse> tripResponses = tripBookingService.getTripHistoryByCustomer(customerId);

        Assertions.assertNotNull(tripResponses);
        
    }
}
