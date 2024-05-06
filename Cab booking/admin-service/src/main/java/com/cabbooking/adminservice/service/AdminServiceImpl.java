package com.cabbooking.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.adminservice.entity.Admin;
import com.cabbooking.adminservice.exception.AdminExceptions;
import com.cabbooking.adminservice.model.TripResponse;
import com.cabbooking.adminservice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TripServiceConsumer tripService;

	@Override
	public Admin createAdmin(Admin admin) throws AdminExceptions {
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminExceptions {
		Optional<Admin> opt = adminRepository.findById(admin.getAdminId());
		if (opt.isPresent()) {

			return adminRepository.save(admin);
		}
		throw new AdminExceptions("Admin not found with id: " + admin.getAdminId());

	}

	@Override
	public Admin deleteAdmin(int id) throws AdminExceptions {
		Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new AdminExceptions("Invalid Id"));
		adminRepository.delete(existingAdmin);

		return existingAdmin;
	}

	@Override
	public List<TripResponse> getAllTrips() throws AdminExceptions {
		List<TripResponse> trips = tripService.getAllTrips();
		return trips;
	}

	@Override
	public List<TripResponse> getTripsDriverwise(int driverId) {
		List<TripResponse> trips = tripService.getTripsByDriverId(driverId);

		if (trips.size() > 0) {
			return trips;
		} else {
			throw new AdminExceptions("No trips found");
		}

	}

	@Override
	public List<TripResponse> getTripsCustomerwise(int customerId) {
		List<TripResponse> trips = tripService.getTripsByCustomerId(customerId);
		if (trips.size() > 0) {
			return trips;
		} else {
			throw new AdminExceptions("No trips found");

		}
	}
}
