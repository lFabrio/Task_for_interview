package main.java.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.core.domain.BankAccount;

public class AccountInMemoryDatabase {

    private Long nextId = 1L;
    private final List<BankAccount> bankAccountList = new ArrayList<>();

    public void createBankAccount(BankAccount bankAccount) {
        bankAccount.setId(nextId);
        nextId++;
        bankAccountList.add(bankAccount);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountList;
    }

    public Optional<BankAccount> findById(Long id) {
        return bankAccountList.stream()
                .filter(bankAccount -> bankAccount.getId().equals(id))
                .findFirst();
    }

}
