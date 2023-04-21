package main.java.console_ui;

import main.java.core.requests.MakePaymentRequest;
import main.java.core.responses.GetAllAccountsResponse;
import main.java.core.responses.MakePaymentResponse;
import main.java.core.services.GetAllAccountsService;
import main.java.core.services.MakePaymentService;

import java.math.BigDecimal;
import java.util.Scanner;

public class MakePaymentUIAction implements UIAction {

    private final MakePaymentService makePaymentService;
    private final GetAllAccountsService getAllAccountsService;

    public MakePaymentUIAction(MakePaymentService makePaymentService, GetAllAccountsService getAllAccountsService) {
        this.makePaymentService = makePaymentService;
        this.getAllAccountsService = getAllAccountsService;
    }

    @Override
    public void execute() {
        printAllAccounts();
        Scanner input = new Scanner(System.in);
        System.out.println("Choose the account ID to withdraw from:");
        Long withdrawAccountId = input.nextLong();
        System.out.println("Choose the account ID to deposit on:");
        Long depositAccountId = input.nextLong();
        System.out.println("Enter the transaction money amount");
        BigDecimal moneyAmount = BigDecimal.valueOf(input.nextDouble());

        MakePaymentRequest request = new MakePaymentRequest(withdrawAccountId, depositAccountId, moneyAmount);
        MakePaymentResponse response = makePaymentService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Transaction successfully done");
        }
    }

    private void printAllAccounts() {
        System.out.println("Account list: ");
        GetAllAccountsResponse response = getAllAccountsService.execute();
        response.getBankAccounts().forEach(System.out::println);
        System.out.println("Account list end.");
    }

}
