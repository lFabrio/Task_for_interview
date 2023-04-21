package main.java.core.responses;

import main.java.core.domain.Payment;

import java.util.List;

public class MakePaymentResponse extends CoreResponse {

    private Payment newPayment;

    public MakePaymentResponse(List<CoreError> errors) {
        super(errors);
    }

    public MakePaymentResponse(Payment newPayment) {
        this.newPayment = newPayment;
    }

    public Payment getNewPayment() {
        return newPayment;
    }
}
