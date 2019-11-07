package local.tylerb.orders.controllers;

import local.tylerb.orders.models.Customers;
import local.tylerb.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // http://localhost:8080/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllOrders() {
        List<Customers> myCustomers = customerService.findAll();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    // http://localhost:8080/customers/customer/2
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {
        Customers myCustomer = customerService.findById(id);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    // http://localhost:8080/customers/namelike/Alex
    @GetMapping(value = "namelike/{namelike}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String namelike) {
        List<Customers> myCustomers = customerService.findCustomerByLikeName(namelike);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }


}
