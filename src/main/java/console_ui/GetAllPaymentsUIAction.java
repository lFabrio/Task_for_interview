package main.java.console_ui;

import main.java.core.responses.GetAllPaymentsResponse;
import main.java.core.services.GetAllPaymentsService;

public class GetAllPaymentsUIAction implements UIAction {

    private final GetAllPaymentsService service;

    public GetAllPaymentsUIAction(GetAllPaymentsService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("Payment list: ");
        GetAllPaymentsResponse response = service.execute();
        response.getPayments().forEach(System.out::println);
        System.out.println("Payment list end.");
    }
}
