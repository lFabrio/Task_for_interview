package main.java.core.services;

import main.java.database.AccountInMemoryDatabase;
import main.java.core.requests.CreateBankAccountRequest;
import main.java.core.responses.CoreError;
import main.java.core.responses.CreateBankAccountResponse;
import main.java.core.validators.CreateBankAccountRequestValidator;
import main.java.core.domain.BankAccount;

import java.util.List;

public class CreateBankAccountService {

    private final AccountInMemoryDatabase database;
    private final CreateBankAccountRequestValidator validator;

    public CreateBankAccountService(AccountInMemoryDatabase database, CreateBankAccountRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public CreateBankAccountResponse execute(CreateBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new CreateBankAccountResponse(errors);
        }

        // Generate account to add to database
        BankAccount bankAccount = new BankAccount(request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPhoneNumber(), request.getPersonalCode());
        database.createBankAccount(bankAccount);

        return new CreateBankAccountResponse(bankAccount);
    }
}
