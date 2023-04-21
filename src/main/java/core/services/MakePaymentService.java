package main.java.core.services;

import main.java.database.AccountInMemoryDatabase;
import main.java.database.PaymentInMemoryDatabase;
import main.java.core.requests.MakePaymentRequest;
import main.java.core.responses.CoreError;
import main.java.core.responses.MakePaymentResponse;
import main.java.core.validators.MakePaymentRequestValidator;
import main.java.core.domain.BankAccount;
import main.java.core.domain.Payment;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;


public class MakePaymentService {

    private final AccountInMemoryDatabase accountDatabase;
    private final PaymentInMemoryDatabase paymentDatabase;
    private final MakePaymentRequestValidator validator;

    public MakePaymentService(AccountInMemoryDatabase accountDatabase,
                              PaymentInMemoryDatabase paymentDatabase,
                              MakePaymentRequestValidator validator) {

        this.accountDatabase = accountDatabase;
        this.paymentDatabase = paymentDatabase;
        this.validator = validator;
    }

    public MakePaymentResponse execute(MakePaymentRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new MakePaymentResponse(errors);
        }

        // Find accounts to operate with
        BankAccount withdrawAccount = accountDatabase.findById(request.getWithdrawAccountId()).get();
        BankAccount depositAccount = accountDatabase.findById(request.getDepositAccountId()).get();

        // Generate new balance for accounts
        BigDecimal newWithdrawAccountBalance = withdrawAccount.getMoneyAmount().subtract(request.getMoneyAmount());
        BigDecimal newDepositAccountBalance = depositAccount.getMoneyAmount().add(request.getMoneyAmount());

        // Set new balance in accounts
        withdrawAccount.setMoneyAmount(newWithdrawAccountBalance);
        depositAccount.setMoneyAmount(newDepositAccountBalance);

        // Generate payment with info to add to database
        Payment payment = new Payment(request.getWithdrawAccountId(), request.getDepositAccountId(),
                request.getMoneyAmount(), Calendar.getInstance().getTime());
        paymentDatabase.addPaymentToDatabase(payment);

        return new MakePaymentResponse(payment);
    }

}
