package local.tylerb.orders.services;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Customers;

import java.util.List;

public interface CustomerService {

    List<Customers> findAll();

    Customers findById(long id);

    List<Customers> findCustomerByLikeName(String name);

    /* GET /customers/namelike/{likename} - Returns all customers and their orders with a customer name containing the given substring
    GET /agents/agent/{id} - Returns the agent and their customers with the given agent id
    GET /orders/order/{id} - Returns the order and its customer with the given order number*/

}
