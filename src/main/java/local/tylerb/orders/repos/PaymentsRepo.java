package local.tylerb.orders.repos;

import local.tylerb.orders.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepo extends CrudRepository<Payments, Long> {
}
