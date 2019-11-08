package local.tylerb.orders.services;

import local.tylerb.orders.models.Customers;
import local.tylerb.orders.models.Orders;
import local.tylerb.orders.models.Payments;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Orders findOrderByID(long id) {
        return ordersRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Orders addOrder(Orders order) {
        Orders newOrder = new Orders();

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        Customers setCustomer = customerService.findById(order.getCustomers().getCustcode());
        newOrder.setCustomers(setCustomer);

        for (Payments p : order.getPayments()){
            Payments newPayment = paymentService.findPaymentById(p.getPaymentid());
            newOrder.getPayments().add(newPayment);
        }

        return ordersRepo.save(newOrder);

    }

    @Override
    public Orders putOrder(Orders order, long id) {
        Orders currentOrder = findOrderByID(id);

        if (order.hasOrdamount) {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if (order.hasAdvanceamount) {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if (order.getOrderdescription() != null) {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

        if (order.getCustomers() != null) {
            currentOrder.setCustomers(customerService.findById(order.getCustomers().getCustcode()));
        }

        if (order.getPayments().size() > 0) {
            for (Payments p : order.getPayments()) {
                Payments newPayment = paymentService.findPaymentById(p.getPaymentid());
                currentOrder.addPayments(newPayment);
            }
        }

        return ordersRepo.save(currentOrder);



    }

    @Override
    public void delete(long id) {
        if (findOrderByID(id) != null) {
            ordersRepo.delete(findOrderByID(id));
        }
    }
}
