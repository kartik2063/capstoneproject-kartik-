package com.cabbooking.tripbookingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.tripbookingservice.entity.TripBooking;
import com.cabbooking.tripbookingservice.exception.NotFoundException;
import com.cabbooking.tripbookingservice.model.Customer;
import com.cabbooking.tripbookingservice.model.Driver;
import com.cabbooking.tripbookingservice.model.DriverResponse;
import com.cabbooking.tripbookingservice.model.TripResponse;
import com.cabbooking.tripbookingservice.repository.TripRepository;

@Service
public class TripBookingServiceImpl implements TripBookingService {
	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private CustomerServiceConsumer customerService;

	@Autowired
	private DriverServiceConsumer driverService;

	@Override
	public TripBooking AddTrip(TripBooking tripBooking) {
		Customer customer = customerService.fetchCustomerDetails(tripBooking.getCustomerId());
		if (customer == null) {
			throw new NotFoundException("Sorry, no customer with id: " + tripBooking.getCustomerId()  );
		}

		List<DriverResponse> availableDrivers = driverService.findByAvailable();
		if (availableDrivers.isEmpty()) {
			throw new NotFoundException("Sorry, no drivers found.");
		}

		DriverResponse selectedDriver = availableDrivers.get(0);
		selectedDriver.setAvailable(false);

		customer.setJourney_status(true);
		customerService.updateCustomer(customer);
		float km = tripBooking.getKm();
		float price = selectedDriver.getCab().getRatePerKm();
		tripBooking.setTotalamount(km * price);
		tripBooking.setDriverId(selectedDriver.getDriverId());

		return tripRepository.save(tripBooking);
	}

	@Override
	public List<TripResponse> alltrip() {
		List<TripBooking> trips = tripRepository.findAll();
		List<TripResponse> tripResponses = new ArrayList<>();

		for (TripBooking tripBooking : trips) {
			TripResponse tripResponse = new TripResponse();

			tripResponse.setTripBookingId(tripBooking.getTripBookingId());
			tripResponse.setCustomerId(tripBooking.getCustomerId());
			tripResponse.setDriverId(tripBooking.getDriverId());
			tripResponse.setTotalamount(tripBooking.getTotalamount());
			tripResponse.setFrom_location(tripBooking.getFrom_location());
			tripResponse.setTo_location(tripBooking.getTo_location());
			tripResponse.setFromdate_time(tripBooking.getFromdate_time());
			tripResponse.setKm(tripBooking.getKm());
			tripResponse.setPayment(tripBooking.getPayment());

			int driverId = tripBooking.getDriverId();
			int customerId = tripBooking.getCustomerId();

			DriverResponse driverResponse = driverService.getDriverDetails(driverId);
			Customer customer = customerService.fetchCustomerDetails(customerId);

			tripResponses.add(tripResponse);
		}
		return tripResponses;
	}

	@Override
	public TripBooking updateTrip(TripBooking tripBooking) {
		Optional<TripBooking> optionalTripBooking = tripRepository.findById(tripBooking.getTripBookingId());
		if (!optionalTripBooking.isPresent()) {
			throw new NotFoundException("Trip with id: " + tripBooking.getTripBookingId() + " not found.");
		}

		TripBooking updatedTrip = optionalTripBooking.get();

		Customer originalCustomer = customerService.fetchCustomerDetails(updatedTrip.getCustomerId());
		DriverResponse originalDriver = driverService.getDriverDetails(updatedTrip.getDriverId());

		// Update only the fields that need to be changed
		float km = tripBooking.getKm();
		float price = originalDriver.getCab().getRatePerKm();

		updatedTrip.setTripBookingId(tripBooking.getTripBookingId());
		updatedTrip.setKm(tripBooking.getKm());
		updatedTrip.setTotalamount(km * price);
		updatedTrip.setFrom_location(tripBooking.getFrom_location());
		updatedTrip.setTo_location(tripBooking.getTo_location());
		updatedTrip.setFromdate_time(tripBooking.getFromdate_time());
		updatedTrip.setTodate_time(tripBooking.getTodate_time());
		updatedTrip.setCustomerId(originalCustomer.getUserId());
		updatedTrip.setDriverId(originalDriver.getDriverId());
		updatedTrip.setPayment(tripBooking.getPayment());

		return tripRepository.save(updatedTrip);
	}

	@Override
	public String deleteTrip(int id) throws NotFoundException {
		Optional<TripBooking> optionalTrip = tripRepository.findById(id);

		if (!optionalTrip.isPresent()) {
			throw new NotFoundException("Trip with id: " + id + " not found.");
		}

		TripBooking trip = optionalTrip.get();

		DriverResponse selectedDriver = driverService.getDriverDetails(trip.getDriverId());
		selectedDriver.setAvailable(true);

		Customer customer = customerService.fetchCustomerDetails(trip.getCustomerId());
		customer.setJourney_status(false);
		customerService.updateCustomer(customer);

		tripRepository.delete(trip);

		return "Trip with id: " + id
				+ " has been successfully deleted. ";
	}



	@Override
	public String tripEnd(int id) throws NotFoundException {
		Optional<TripBooking> optionalTrip = tripRepository.findById(id);

		if (!optionalTrip.isPresent()) {
			throw new NotFoundException("Trip with id: " + id + " not found.");
		}

		TripBooking trip = optionalTrip.get();

		DriverResponse selectedDriver = driverService.getDriverDetails(trip.getDriverId());
		selectedDriver.setAvailable(true);

		Customer customer = customerService.fetchCustomerDetails(trip.getCustomerId());
		customer.setJourney_status(false);
		customerService.updateCustomer(customer);

		return "Trip with id: " + id
				+ " has been successfully canceled.";
	}

	@Override
	public List<TripResponse> getTripHistoryByCustomer(int customerId) {
		List<TripBooking> trips = tripRepository.findByCustomerId(customerId);
		List<TripResponse> tripResponses = new ArrayList<>();

		for (TripBooking tripBooking : trips) {
			TripResponse tripResponse = new TripResponse();

			tripResponse.setTripBookingId(tripBooking.getTripBookingId());
			tripResponse.setCustomerId(tripBooking.getCustomerId());
			tripResponse.setDriverId(tripBooking.getDriverId());
			tripResponse.setTotalamount(tripBooking.getTotalamount());
			tripResponse.setFrom_location(tripBooking.getFrom_location());
			tripResponse.setTo_location(tripBooking.getTo_location());
			tripResponse.setFromdate_time(tripBooking.getFromdate_time());
			tripResponse.setKm(tripBooking.getKm());
			tripResponse.setPayment(tripBooking.getPayment());

			DriverResponse driverResponse = driverService.getDriverDetails(tripBooking.getDriverId());
			Customer customer = customerService.fetchCustomerDetails(tripBooking.getCustomerId());

			

			tripResponses.add(tripResponse);
		}

		return tripResponses;
	}




}
