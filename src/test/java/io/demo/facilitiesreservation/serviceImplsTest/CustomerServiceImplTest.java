package io.demo.facilitiesreservation.serviceImplsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.demo.facilitiesreservation.entities.Customer;
import io.demo.facilitiesreservation.repositories.CustomerRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.serviceImpls.CustomerServiceImpl;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    // This method initializes the mocks before each test
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomer_ValidId() {
        // Arrange
        UUID customerId = UUID.randomUUID();
        Customer mockCustomer = new Customer();
        mockCustomer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

        // Act
        Customer result = customerService.getCustomer(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getId());
    }

    @Test
    void testGetCustomer_InvalidId() {
        // Arrange
        UUID invalidCustomerId = UUID.randomUUID();
        when(customerRepository.findById(invalidCustomerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> customerService.getCustomer(invalidCustomerId));
    }

}
