package main.java.core.responses;

import main.java.core.domain.BankAccount;

import java.util.List;

public class GetAllAccountsResponse extends CoreResponse {

    private final List<BankAccount> bankAccounts;

    public GetAllAccountsResponse(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
