package com.cabbooking.driverservice.service;

import java.util.List;

import com.cabbooking.driverservice.entity.Driver;
import com.cabbooking.driverservice.exception.DriverNotFoundException;
import com.cabbooking.driverservice.model.DriverResponse;


public interface DriverService {
	public Driver insertDriver(Driver driver);

	public DriverResponse viewDriverById(int driverId) throws DriverNotFoundException;
	
	List<DriverResponse> getAllDrivers();

	public Driver updateDriver(Driver driver) throws DriverNotFoundException;

	public String deleteDriverById(int id) throws DriverNotFoundException;

	public List<DriverResponse> viewBestDriver() throws DriverNotFoundException;
	
	public List<DriverResponse> findByAvailabble() throws DriverNotFoundException;
	
}
