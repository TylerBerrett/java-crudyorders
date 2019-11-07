package local.tylerb.orders.services;

import local.tylerb.orders.models.Orders;

public interface OrderService {

    Orders findOrderByID(long id);
}
