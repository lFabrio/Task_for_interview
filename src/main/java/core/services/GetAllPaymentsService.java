package main.java.core.services;

import main.java.database.PaymentInMemoryDatabase;
import main.java.core.responses.GetAllPaymentsResponse;

public class GetAllPaymentsService {

    private final PaymentInMemoryDatabase database;

    public GetAllPaymentsService(PaymentInMemoryDatabase database) {
        this.database = database;
    }

    public GetAllPaymentsResponse execute() {
        return new GetAllPaymentsResponse(database.getPaymentList());
    }
}
