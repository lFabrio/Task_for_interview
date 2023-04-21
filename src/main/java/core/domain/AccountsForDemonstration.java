package main.java.core.domain;

import main.java.database.AccountInMemoryDatabase;

import java.math.BigDecimal;

public class AccountsForDemonstration {

    private final AccountInMemoryDatabase accountDatabase;

    public AccountsForDemonstration(AccountInMemoryDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
    }

    // Some accounts adding to database for service tests
    public void execute() {
        BankAccount bankAccount1 = new BankAccount("firstName1", "lastName1", "sadasda@asd.asd",
                "+371123123123", "12121210101");
        BankAccount bankAccount2 = new BankAccount("firstName2", "lastName2", "sadasda@asd.asd",
                "+371123123123", "12121210102");
        BankAccount bankAccount3 = new BankAccount("firstName3", "lastName3", "sadasda@asd.asd",
                "+371123123123", "12121210103");
        BankAccount bankAccount4 = new BankAccount("firstName4", "lastName4", "sadasda@asd.asd",
                "+371123123123", "12121210104");
        bankAccount1.setMoneyAmount(BigDecimal.valueOf(100));
        bankAccount2.setMoneyAmount(BigDecimal.valueOf(1000));
        bankAccount3.setMoneyAmount(BigDecimal.valueOf(31));
        bankAccount4.setMoneyAmount(BigDecimal.valueOf(75648));
        accountDatabase.createBankAccount(bankAccount1);
        accountDatabase.createBankAccount(bankAccount2);
        accountDatabase.createBankAccount(bankAccount3);
        accountDatabase.createBankAccount(bankAccount4);
    }
}
