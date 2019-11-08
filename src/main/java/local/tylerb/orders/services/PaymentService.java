package local.tylerb.orders.services;

import local.tylerb.orders.models.Payments;

public interface PaymentService {
    Payments findPaymentById(long id);
}
