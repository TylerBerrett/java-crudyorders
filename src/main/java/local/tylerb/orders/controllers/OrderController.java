package local.tylerb.orders.controllers;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Orders;
import local.tylerb.orders.services.AgentService;
import local.tylerb.orders.services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // http://localhost:8080/orders/order/2
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderById(@PathVariable long id) {
        Orders myOrder = orderService.findOrderByID(id);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }

    // http://localhost:8080/orders/order
    @PostMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<?> createOrder(@RequestBody Orders order) {
        order = orderService.addOrder(order);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI orderUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrdnum())
                .toUri();

        responseHeaders.setLocation(orderUri);

        return new ResponseEntity<>(order.getOrdnum(), responseHeaders, HttpStatus.CREATED);
    }

    // http://localhoast:8080/orders/order/53
    @PutMapping(value = "/order/{ordernum}")
    public ResponseEntity<?> updateOrder(@RequestBody Orders order, @PathVariable long ordernum) {
        orderService.putOrder(order, ordernum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:8080/orders/order/53
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long id){
        orderService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
