package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.exception.OrderValidationException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsValidator implements Validator<CustomerDetailsRequest> {

    private final static String VALID_PHONE_REGEX = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{3}\\)?[-.\\s]?)?(\\d[-.\\s]?){6,9}\\d$";

    @Override
    public void validate(CustomerDetailsRequest customerDetails) {
        validateName(customerDetails.getName());
        validateEmail(customerDetails.getEmail());
        validatePhoneNumber(customerDetails.getPhone());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new OrderValidationException("Customer name cannot be null or empty");
        }
    }

    private void validateEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate(); // This validates the email format
        } catch (AddressException e) {
            throw new OrderValidationException("Invalid email provided: " + email);
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new OrderValidationException("Phone number cannot be empty");
        }

        if (!phoneNumber.matches(VALID_PHONE_REGEX)) {
            throw new OrderValidationException("Invalid phone number provided: " + phoneNumber);
        }

    }
}
