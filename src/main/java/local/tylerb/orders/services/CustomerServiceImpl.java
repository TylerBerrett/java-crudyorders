package local.tylerb.orders.services;

import local.tylerb.orders.models.Customers;
import local.tylerb.orders.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomersRepo customersRepo;

    @Override
    public List<Customers> findAll() {
        List<Customers> list = new ArrayList<>();
        customersRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findById(long id) {
        return customersRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Customers> findCustomerByLikeName(String name) {
        return customersRepo.findByCustnameIgnoreCase(name);
    }
}
