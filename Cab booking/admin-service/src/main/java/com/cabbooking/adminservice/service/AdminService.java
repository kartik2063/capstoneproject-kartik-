package com.cabbooking.adminservice.service;

import java.util.List;

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.exception.AdminExceptions;
import com.cabbooking.adminservice.model.TripResponse;

public interface AdminService {

	public Admin createAdmin(Admin admin) throws AdminExceptions;
	
	public Admin updateAdmin(Admin admin) throws AdminExceptions;
	
	public Admin deleteAdmin(int id) throws AdminExceptions;

	public List<TripResponse> getAllTrips() throws AdminExceptions;
	
	public List<TripResponse> getTripsDriverwise(int driverId);

	public List<TripResponse> getTripsCustomerwise(int customerId);
	}
