package main.java.core.responses;

import main.java.core.domain.BankAccount;

import java.util.List;

public class CreateBankAccountResponse extends CoreResponse {

    private BankAccount newBankAccount;

    public CreateBankAccountResponse(List<CoreError> errors) {
        super(errors);
    }

    public CreateBankAccountResponse(BankAccount newBankAccount) {
        this.newBankAccount = newBankAccount;
    }

    public BankAccount getNewBankAccount() {
        return newBankAccount;
    }

}
