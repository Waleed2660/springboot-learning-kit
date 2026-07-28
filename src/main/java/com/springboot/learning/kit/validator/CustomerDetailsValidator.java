package com.springboot.learning.kit.validator;

import com.springboot.learning.kit.domain.CustomerDetails;
import com.springboot.learning.kit.dto.request.CustomerDetailsRequest;
import com.springboot.learning.kit.exception.OrderValidationException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsValidator implements Validator<CustomerDetailsRequest> {

    private final static String PHONE_VALIDATION_REGEX = "^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{3}\\)?[-.\\s]?)?(\\d[-.\\s]?){6,9}\\d$";

    @Override
    public void validate(CustomerDetailsRequest object) throws OrderValidationException {
        validateEmail(object.getEmail());
        validateName(object.getName());
        validatePhoneNumber(object.getPhone());
    }

    private void validateEmail(String email) throws OrderValidationException {
        if (email == null || email.isEmpty()) {
            throw new OrderValidationException("Missing email field from order");
        }
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            throw new OrderValidationException("Invalid email field from order");
        }
    }

    private void validateName(String name) throws OrderValidationException {
        if (name == null || name.isEmpty()) {
            throw new OrderValidationException("Missing name field from order");
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws OrderValidationException {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new OrderValidationException("Missing phone number field from order");
        }
        if (!phoneNumber.matches(PHONE_VALIDATION_REGEX)) {
            throw new OrderValidationException("Invalid phone number field from order");
        }
    }
}
