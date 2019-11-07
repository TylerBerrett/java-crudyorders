package local.tylerb.orders.repos;

import local.tylerb.orders.models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CustomersRepo extends CrudRepository<Customers, Long> {

    ArrayList<Customers> findByCustnameIgnoreCase(String likename);
}
