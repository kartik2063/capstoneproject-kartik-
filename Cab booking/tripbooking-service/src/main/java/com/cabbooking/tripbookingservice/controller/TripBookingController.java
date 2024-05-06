package com.cabbooking.tripbookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.tripbookingservice.entity.TripBooking;
import com.cabbooking.tripbookingservice.model.TripResponse;
import com.cabbooking.tripbookingservice.service.TripBookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tripBooking")
public class TripBookingController {

	@Autowired
	TripBookingService tripBookingService;

	@PostMapping("/add")
	public ResponseEntity<TripBooking> Book(@Valid @RequestBody TripBooking trip) {
		TripBooking tripBooking = tripBookingService.AddTrip(trip);
		return new ResponseEntity<TripBooking>(tripBooking, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public List<TripResponse> getAllTrips() {
		List<TripResponse> tripBookings = tripBookingService.alltrip();
		return tripBookings;
	}

	@PutMapping("/edit")
	public ResponseEntity<TripBooking> updateBooking(@Valid @RequestBody TripBooking trip) {

		tripBookingService.updateTrip(trip);
		ResponseEntity<TripBooking> responseEntity = new ResponseEntity<>(trip, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {

		return tripBookingService.deleteTrip(id);
	}
	
}