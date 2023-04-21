package main.java.core.domain;

import java.math.BigDecimal;

public class BankAccount {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private final String personalCode;
    private BigDecimal moneyAmount;

    public BankAccount(String firstName, String lastName, String email, String phoneNumber, String personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personalCode = personalCode;
        this.moneyAmount = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personalCode='" + personalCode + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
