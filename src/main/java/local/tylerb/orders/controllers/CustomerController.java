package local.tylerb.orders.controllers;

import local.tylerb.orders.models.Customers;
import local.tylerb.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // http://localhost:8080/customers/customer
    @PostMapping(value = "/customer", produces = {"application/json"})
    public ResponseEntity<?> addCustomer (@Valid @RequestBody Customers newCustomer){
        newCustomer = customerService.add(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI customerURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();

        responseHeaders.setLocation(customerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:8080/customers/customer/40
    @PutMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode) {
        customerService.put(updateCustomer, custcode);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    // http://localhost:8080/customers/customer/40
    @DeleteMapping(value = "/customer/{custcode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode){
        customerService.delete(custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
