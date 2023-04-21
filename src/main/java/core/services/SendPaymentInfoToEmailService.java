package main.java.core.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import main.java.core.domain.BankAccount;
import main.java.core.domain.Payment;
import main.java.database.AccountInMemoryDatabase;
import main.java.database.PaymentInMemoryDatabase;

import java.util.Optional;
import java.util.Properties;

public class SendPaymentInfoToEmailService {

    private final PaymentInMemoryDatabase paymentInMemoryDatabase;
    private final AccountInMemoryDatabase accountInMemoryDatabase;

    public SendPaymentInfoToEmailService(PaymentInMemoryDatabase paymentInMemoryDatabase,
                                         AccountInMemoryDatabase accountInMemoryDatabase) {
        this.paymentInMemoryDatabase = paymentInMemoryDatabase;
        this.accountInMemoryDatabase = accountInMemoryDatabase;
    }

    public void execute(Long id, String email) {

        // Receiver's email address
        String emailTo = email;

        // Sender's email address
        String emailFrom = "vlad_test@inbox.lv";

        // Sender's email password
        String password = "4V3faAjTS3";

        // SMTP server hostname
        String host = "mail.inbox.lv";

        // Set properties for sending email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Create a session with the properties
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);

            // Set the sender, receiver, subject, and body
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Info about payment");
            message.setContent(generateMessage(id), "text/html");

            // Send the message
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e.getMessage());;
        }
    }

    private String generateMessage(Long id) throws MessagingException {
        Payment payment = getPayment(id);
        BankAccount withdrawAccount = getBankAccount(payment.getWithdrawAccountId());
        BankAccount depositAccount = getBankAccount(payment.getDepositAccountId());

        return alignMessageText(payment, withdrawAccount, depositAccount);
    }

    // Validates payment existence
    private Payment getPayment(Long id) throws MessagingException {
        Optional<Payment> paymentOptional = paymentInMemoryDatabase.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new MessagingException("Payment does not exist");
        }
        return paymentOptional.get();
    }

    // Validates account existence
    private BankAccount getBankAccount(Long id) throws MessagingException {
        Optional<BankAccount> bankAccountOptional = accountInMemoryDatabase.findById(id);
        if (bankAccountOptional.isEmpty()) {
            throw new MessagingException("Account does not exist");
        }
        return bankAccountOptional.get();
    }

    // Returns aligned message with html tags
    private String alignMessageText(Payment payment, BankAccount withdrawAccount, BankAccount depositAccount) {
        return "<div style=\"text-align: center;\">Information about payment with id: "
                + payment.getId() + "</div><br><br><br>"
                + "<div style=\"text-align: left;\">Sender: " + withdrawAccount.getFirstName() + " "
                + withdrawAccount.getLastName() + "<br>"
                + "Receiver: " + depositAccount.getFirstName() + " " + depositAccount.getLastName() + "<br>"
                + "Money amount: " + payment.getMoneyAmount().toString() + "</div><br><br><br>"
                + "<div style=\"text-align: right;\">Date: " + payment.getPaymentDate() + "</div>";
    }

}

