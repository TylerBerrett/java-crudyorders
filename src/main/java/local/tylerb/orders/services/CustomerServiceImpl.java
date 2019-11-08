package local.tylerb.orders.services;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Customers;
import local.tylerb.orders.models.Orders;
import local.tylerb.orders.models.Payments;
import local.tylerb.orders.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomersRepo customersRepo;

    @Autowired
    private AgentService agentService;

    @Autowired
    private PaymentService paymentService;



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

    @Transactional
    @Override
    public Customers add(Customers customer) {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

        Agents newAgent = agentService.findById(customer.getAgents().getAgentcode());
        newCustomer.setAgents(newAgent);

        for (Orders o : customer.getOrders()){
            Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            for (Payments p : o.getPayments()){
                Payments newPayment = paymentService.findPaymentById(p.getPaymentid());
                newOrder.getPayments().add(newPayment);

            }

            newCustomer.getOrders().add(newOrder);
        }



        return customersRepo.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers put(Customers customer, long id) {
        Customers current = findById(id);

        if (customer.getCustname() != null) {
            current.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null) {
            current.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null) {
            current.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null) {
            current.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null) {
            current.setGrade(customer.getGrade());
        }
        if (customer.hasOpen) {
            current.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.hasReceive) {
            current.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.hasPayment) {
            current.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.hasOutstanding) {
            current.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null) {
            current.setPhone(customer.getPhone());
        }
        if (customer.getAgents() != null){
            current.setAgents(customer.getAgents());
        }

        return customersRepo.save(current);
    }

    @Override
    public void delete(long id) {
        if (findById(id) != null) {
            customersRepo.delete(findById(id));
        }
    }
}
