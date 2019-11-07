package local.tylerb.orders.controllers;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Orders;
import local.tylerb.orders.services.AgentService;
import local.tylerb.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // http://localhost:8080/orders/order/2
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAgentById(@PathVariable long id) {
        Orders myOrder = orderService.findOrderByID(id);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }


}
