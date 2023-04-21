package main.java.core.responses;

import main.java.core.domain.Payment;

import java.util.List;

public class GetAllPaymentsResponse extends CoreResponse {

    private final List<Payment> payments;

    public GetAllPaymentsResponse(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
