package main.java.core.services;

import main.java.database.AccountInMemoryDatabase;
import main.java.core.responses.GetAllAccountsResponse;

public class GetAllAccountsService {

    private final AccountInMemoryDatabase database;

    public GetAllAccountsService(AccountInMemoryDatabase database) {
        this.database = database;
    }

    public GetAllAccountsResponse execute() {
        return new GetAllAccountsResponse(database.getAllAccounts());
    }
}
