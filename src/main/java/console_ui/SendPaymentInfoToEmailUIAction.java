package main.java.console_ui;

import main.java.core.responses.GetAllPaymentsResponse;
import main.java.core.services.GetAllPaymentsService;
import main.java.core.services.SendPaymentInfoToEmailService;

import java.util.Scanner;

public class SendPaymentInfoToEmailUIAction implements UIAction {

    private final SendPaymentInfoToEmailService sendPaymentInfoToEmailService;
    private final GetAllPaymentsService getAllPaymentsService;

    public SendPaymentInfoToEmailUIAction(SendPaymentInfoToEmailService sendPaymentInfoToEmailService,
                                          GetAllPaymentsService getAllPaymentsService) {
        this.sendPaymentInfoToEmailService = sendPaymentInfoToEmailService;
        this.getAllPaymentsService = getAllPaymentsService;
    }

    @Override
    public void execute() {
        printAllPayments();
        System.out.println("Select the payment ID to send by email");
        Scanner scanner = new Scanner(System.in);
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Type email you want to send to");
        String email = scanner.nextLine();
        sendPaymentInfoToEmailService.execute(id, email);
        System.out.println("Email sent successfully!");
    }

    private void printAllPayments() {
        System.out.println("Payment list: ");
        GetAllPaymentsResponse response = getAllPaymentsService.execute();
        response.getPayments().forEach(System.out::println);
        System.out.println("Payment list end.");
    }

}
