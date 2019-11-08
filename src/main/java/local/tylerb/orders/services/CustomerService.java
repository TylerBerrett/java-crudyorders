package local.tylerb.orders.services;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Customers;

import java.util.List;

public interface CustomerService {

    List<Customers> findAll();

    Customers findById(long id);

    List<Customers> findCustomerByLikeName(String name);

    Customers add(Customers customer);

    Customers put(Customers customer, long id);

    void delete(long id);

}
