package com.sunglowsys.resource;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.resource.util.BadRequestException;
import com.sunglowsys.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        log.debug("REST request to create Customer : {}", customer);
        if (customer.getId() != null){
            throw new BadRequestException("Id should be null in create api call");
        }
        Customer result = customerService.createCustomer(customer);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestBody Pageable pageable){
        log.debug("REST request to getAll Customers : {}", pageable.toString());
        Page<Customer> result = customerService.findAll(pageable);
        return ResponseEntity
                .ok()
                .body(result.getContent());
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        log.debug("REST request to get Customer : {}",id);
        Optional<Customer> result = customerService.findById(id);
        return ResponseEntity
                .ok()
                .body(result.get());
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id){
        log.debug("REST request to update Customer : {}", id);
        Customer result = customerService.update(customer,id);
        return ResponseEntity
                .ok()
                .body(result);
    }

        @DeleteMapping("/customer/{id}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
