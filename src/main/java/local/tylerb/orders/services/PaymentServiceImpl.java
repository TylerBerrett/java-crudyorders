package local.tylerb.orders.services;

import local.tylerb.orders.models.Payments;
import local.tylerb.orders.repos.PaymentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentsRepo paymentrepos;

    @Override
    public Payments findPaymentById(long id) {
        return paymentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment " + id + " not found"));
    }
}