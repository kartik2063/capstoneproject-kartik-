package com.cabbooking.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.model.TripResponse;
import com.cabbooking.adminservice.service.AdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/create")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {
		Admin savedAdmin = adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
		Admin updatedAdmin = adminService.updateAdmin(admin);
		return new ResponseEntity<String>("admin updated " + updatedAdmin, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteMappingHandler(@PathVariable("adminId") int adminId) {
		Admin admin = adminService.deleteAdmin(adminId);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	@GetMapping("/trips/all")
	public ResponseEntity<List<TripResponse>> getAllTrips() {

		List<TripResponse> trips = adminService.getAllTrips();
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	@GetMapping("/trips/driverwise/{id}")
	public ResponseEntity<List<TripResponse>> getTripsDriverwiseHandler(@PathVariable("id") int driverId) {

		List<TripResponse> trips = adminService.getTripsDriverwise(driverId);
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}

	

	@GetMapping("/trips/customertrips/{id}")
	public List<TripResponse> getTripsCustomerwiseHandler(@PathVariable("id") int customerId) {
		List<TripResponse> list = adminService.getTripsCustomerwise(customerId);
		return list;
	}
	}
