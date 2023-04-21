package main.java.database;

import main.java.core.domain.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentInMemoryDatabase {

    private Long nextId = 1L;
    private final List<Payment> paymentList = new ArrayList<>();

    public void addPaymentToDatabase(Payment payment) {
        payment.setId(nextId);
        nextId++;
        paymentList.add(payment);
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public Optional<Payment> findById(Long id) {
        return paymentList.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst();
    }

}
