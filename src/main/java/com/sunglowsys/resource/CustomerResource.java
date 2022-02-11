package com.sunglowsys.resource;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> create(@RequestBody Customer customer){
        Customer result = customerService.createCustomer(customer);
        return ResponseEntity.ok().body("Customer is created successfully: " + result);
    }

    @GetMapping
    public List<Customer> getAll(){
        List<Customer> result1 = customerService.findAll();
        return result1;
    }

    @GetMapping("find_customer/{id}")
    public Customer getById(@PathVariable("id") Integer id){
        return customerService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable("id") Integer id){
        customerService.update(customer, id);
        return ResponseEntity.ok().body("Customer is updated successfully: " + id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        customerService.delete(id);
        return ResponseEntity.ok().body("Customer is successfully Deleted on this ID: " + id);
    }
}
