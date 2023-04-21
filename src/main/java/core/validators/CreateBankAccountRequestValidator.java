package main.java.core.validators;

import main.java.core.requests.CreateBankAccountRequest;
import main.java.core.responses.CoreError;
import main.java.core.domain.FieldEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateBankAccountRequestValidator {

    public List<CoreError> validate(CreateBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        validatePhoneNumber(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(CreateBankAccountRequest request) {
        return (request.getFirstName() == null || request.getFirstName().isBlank())
                ? Optional.of(new CoreError(FieldEnum.FIRSTNAME, "Empty or wrong first name"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(CreateBankAccountRequest request) {
        return (request.getLastName() == null || request.getLastName().isBlank())
                ? Optional.of(new CoreError(FieldEnum.LASTNAME, "Empty or wrong last name"))
                : Optional.empty();
    }

    // Validate email (should be with @ at the middle and end with . and domain)
    private Optional<CoreError> validateEmail(CreateBankAccountRequest request) {
        return (request.getEmail() == null || !request.getEmail().matches("\\w+@\\w+.\\D+"))
                ? Optional.of(new CoreError(FieldEnum.EMAIL, "Empty or wrong email"))
                : Optional.empty();
    }

    // Validate phone number (should start with + and contain 8-13 numbers)
    private Optional<CoreError> validatePhoneNumber(CreateBankAccountRequest request) {
        return (request.getEmail() == null || !request.getPhoneNumber().matches("^[+]?\\d{8,13}"))
                ? Optional.of(new CoreError(FieldEnum.PHONE_NUMBER, "Empty or wrong phone number"))
                : Optional.empty();
    }

    // Validate personal code (should contain 11 numbers)
    private Optional<CoreError> validatePersonalCode(CreateBankAccountRequest request) {
        return (request.getPersonalCode() == null ||
                request.getPersonalCode().length() != 11 ||
                !request.getPersonalCode().matches("\\d+"))
                ? Optional.of(new CoreError(FieldEnum.PERSONAL_CODE, "Empty or wrong personal code"))
                : Optional.empty();
    }
}
