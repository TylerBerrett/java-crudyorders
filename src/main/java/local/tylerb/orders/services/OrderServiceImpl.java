package local.tylerb.orders.services;

import local.tylerb.orders.models.Customers;
import local.tylerb.orders.models.Orders;
import local.tylerb.orders.repos.CustomersRepo;
import local.tylerb.orders.repos.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Override
    public Orders findOrderByID(long id) {
        return ordersRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
