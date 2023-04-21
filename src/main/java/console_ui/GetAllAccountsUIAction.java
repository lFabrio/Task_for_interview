package main.java.console_ui;

import main.java.core.responses.GetAllAccountsResponse;
import main.java.core.services.GetAllAccountsService;

public class GetAllAccountsUIAction implements UIAction {

    private final GetAllAccountsService service;

    public GetAllAccountsUIAction(GetAllAccountsService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("Account list: ");
        GetAllAccountsResponse response = service.execute();
        response.getBankAccounts().forEach(System.out::println);
        System.out.println("Account list end.");
    }
}
