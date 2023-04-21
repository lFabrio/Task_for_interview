package main.java.console_ui;

import main.java.core.requests.CreateBankAccountRequest;
import main.java.core.responses.CreateBankAccountResponse;
import main.java.core.services.CreateBankAccountService;

import java.util.Scanner;

public class CreateBankAccountUIAction implements UIAction {

    private final CreateBankAccountService createBankAccountService;

    public CreateBankAccountUIAction(CreateBankAccountService createBankAccountService) {
        this.createBankAccountService = createBankAccountService;
    }

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your First Name");
        String firstName = input.nextLine();
        System.out.println("Enter your Last Name");
        String lastName = input.nextLine();
        System.out.println("Enter your e-mail");
        String email = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.nextLine();
        System.out.println("Enter your personal code");
        String personalCode = input.nextLine();

        CreateBankAccountRequest request = new CreateBankAccountRequest(firstName, lastName, email, phoneNumber, personalCode);
        CreateBankAccountResponse response = createBankAccountService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New bank account is created with id: " + response.getNewBankAccount().getId());
        }
    }
}
