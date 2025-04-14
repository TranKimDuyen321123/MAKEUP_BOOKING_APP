
package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<User> getAllCustomers() {
        return customerRepository.findByRole("CUSTOMER");
    }

    public User getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public User updateCustomer(Long id, User updatedUser) {
        User existing = customerRepository.findById(id).orElse(null);
        if (existing != null && "CUSTOMER".equals(existing.getRole())) {
            existing.setUsername(updatedUser.getUsername());
            existing.setEmail(updatedUser.getEmail());
            existing.setPasswordHash(updatedUser.getPasswordHash());
            return customerRepository.save(existing);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
