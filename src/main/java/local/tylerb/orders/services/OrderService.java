package local.tylerb.orders.services;

import local.tylerb.orders.models.Orders;

public interface OrderService {

    Orders findOrderByID(long id);

    Orders addOrder(Orders order);

    Orders putOrder(Orders order, long id);

    void delete(long id);


}
