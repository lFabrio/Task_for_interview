package main.java.core.requests;

public class CreateBankAccountRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private final String personalCode;

    public CreateBankAccountRequest(String firstName, String lastName, String email, String phoneNumber, String personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personalCode = personalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
