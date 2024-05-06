package com.bookingservice.customerservice.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cabbooking.customerservice.entity.Address;
import com.cabbooking.customerservice.entity.Customer;
import com.cabbooking.customerservice.exception.IdInvalid;
import com.cabbooking.customerservice.repository.AddressRepository;
import com.cabbooking.customerservice.repository.CustomerRepository;
import com.cabbooking.customerservice.service.CustomerServiceImpl;
import com.google.common.base.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceImplTest {

    
   
}

