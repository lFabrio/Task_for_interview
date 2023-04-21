package main.java.core.validators;

import main.java.database.AccountInMemoryDatabase;
import main.java.core.requests.MakePaymentRequest;
import main.java.core.responses.CoreError;
import main.java.core.domain.BankAccount;
import main.java.core.domain.FieldEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MakePaymentRequestValidator {

    private final AccountInMemoryDatabase accountDatabase;

    public MakePaymentRequestValidator(AccountInMemoryDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    public List<CoreError> validate(MakePaymentRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateWithdrawAccountId(request).ifPresent(errors::add);
        validateDepositAccountId(request).ifPresent(errors::add);
        validateTransactionMoneyAmount(request).ifPresent(errors::add);
        validateWithdrawAccountBalance(request).ifPresent(errors::add);
        validateDepositAccountExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateWithdrawAccountId(MakePaymentRequest request) {
        return (request.getWithdrawAccountId() == null || request.getWithdrawAccountId() <= 0)
                ? Optional.of(new CoreError(FieldEnum.WITHDRAW_ACCOUNT_ID, "Not correct withdraw account ID"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDepositAccountId(MakePaymentRequest request) {
        return (request.getDepositAccountId() == null || request.getDepositAccountId() <= 0)
                ? Optional.of(new CoreError(FieldEnum.DEPOSIT_ACCOUNT_ID, "Not correct deposit account ID"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTransactionMoneyAmount(MakePaymentRequest request) {
        return (request.getMoneyAmount() == null || request.getMoneyAmount().compareTo(BigDecimal.ZERO) <= 0)
                ? Optional.of(new CoreError(FieldEnum.TRANSACTION_MONEY_AMOUNT,
                "Not correct transaction money amount"))
                : Optional.empty();
    }

    private Optional<CoreError> validateWithdrawAccountBalance(MakePaymentRequest request) {
        Optional<BankAccount> withdrawAccountOptional = accountDatabase.findById(request.getWithdrawAccountId());
        if (withdrawAccountOptional.isPresent()) {
            BankAccount withdrawAccount = withdrawAccountOptional.get();
            return (withdrawAccount.getMoneyAmount().compareTo(request.getMoneyAmount()) < 0)
                    ? Optional.of(new CoreError(FieldEnum.TRANSACTION_MONEY_AMOUNT,
                    "Withdraw account does not have enough money to make the transaction"))
                    : Optional.empty();
        }
        return Optional.of(new CoreError(FieldEnum.WITHDRAW_ACCOUNT_ID, "No account with such ID to withdraw"));
    }

    private Optional<CoreError> validateDepositAccountExistence(MakePaymentRequest request) {
        Optional<BankAccount> depositAccountOptional = accountDatabase.findById(request.getDepositAccountId());
        return (depositAccountOptional.isEmpty())
                ? Optional.of(new CoreError(FieldEnum.DEPOSIT_ACCOUNT_ID, "No account with such ID to deposit"))
                : Optional.empty();
    }
}
