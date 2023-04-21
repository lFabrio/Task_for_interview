package main.java;

import main.java.console_ui.*;
import main.java.core.domain.AccountsForDemonstration;
import main.java.core.services.*;
import main.java.database.AccountInMemoryDatabase;
import main.java.database.PaymentInMemoryDatabase;
import main.java.core.validators.CreateBankAccountRequestValidator;
import main.java.core.validators.MakePaymentRequestValidator;

import java.util.Scanner;

public class Main {

    private static AccountInMemoryDatabase accountDatabase = new AccountInMemoryDatabase();
    private static PaymentInMemoryDatabase paymentDatabase = new PaymentInMemoryDatabase();
    private static AccountsForDemonstration accountsForDemonstration = new AccountsForDemonstration(accountDatabase);
    private static CreateBankAccountRequestValidator createAccountValidator = new CreateBankAccountRequestValidator();
    private static MakePaymentRequestValidator makePaymentValidator = new MakePaymentRequestValidator(accountDatabase);
    private static CreateBankAccountService createBankAccountService = new CreateBankAccountService(accountDatabase,
            createAccountValidator);
    private static GetAllAccountsService getAllAccountsService = new GetAllAccountsService(accountDatabase);
    private static GetAllPaymentsService getAllPaymentsService = new GetAllPaymentsService(paymentDatabase);
    private static MakePaymentService makePaymentService = new MakePaymentService(accountDatabase,
            paymentDatabase,
            makePaymentValidator);
    private static SendPaymentInfoToEmailService infoToEmailService = new SendPaymentInfoToEmailService(paymentDatabase,
            accountDatabase);
    private static UIAction createBankAccountUIAction = new CreateBankAccountUIAction(createBankAccountService);
    private static UIAction getAllAccountsUIAction = new GetAllAccountsUIAction(getAllAccountsService);
    private static UIAction makePaymentUIAction = new MakePaymentUIAction(makePaymentService, getAllAccountsService);
    private static UIAction getAllPaymentsUIAction = new GetAllPaymentsUIAction(getAllPaymentsService);
    private static UIAction infoToEmailUIAction = new SendPaymentInfoToEmailUIAction(infoToEmailService,
            getAllPaymentsService);
    private static UIAction exitUIAction = new ExitUIAction();

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        while (true) {
            printMenu();
            int userChoice = userChoiceFromMenu();
            if (userChoice < 0 || userChoice > 6) {
                showErrorMessage();
            } else {
                executeSelectedMenuItem(userChoice);
            }
        }
    }

    private static void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Create new bank account");
        System.out.println("2. Show all bank accounts");
        System.out.println("3. Make new payment");
        System.out.println("4. Show all payments");
        System.out.println("5. Send payment details to email");
        System.out.println("6. Exit");
        System.out.println("0. Add accounts for payment demonstration");
        System.out.println();
    }

    private static void showErrorMessage() {
        System.out.println("Wrong input, try again, please use only 1 to 6 for main menu selection.");
        System.out.println();
    }

    private static void executeSelectedMenuItem(int userChoice) {
        switch (userChoice) {
            case 1 -> createBankAccountUIAction.execute();
            case 2 -> getAllAccountsUIAction.execute();
            case 3 -> makePaymentUIAction.execute();
            case 4 -> getAllPaymentsUIAction.execute();
            case 5 -> infoToEmailUIAction.execute();
            case 6 -> exitUIAction.execute();

            // Test case
            case 0 -> accountsForDemonstration.execute();
        }
    }

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt();
    }
}