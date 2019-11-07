package local.tylerb.orders.repos;

import local.tylerb.orders.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepo extends CrudRepository<Orders, Long> {
}
