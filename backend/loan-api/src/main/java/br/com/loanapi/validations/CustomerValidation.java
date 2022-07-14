package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;

import java.util.Date;

import static br.com.loanapi.utils.RegexPatterns.*;

public class CustomerValidation {

    public boolean validateRequest(CustomerDTO customer){
        notNull(customer);
        verifyName(customer.getName());
        verifyLastName(customer.getLastName());
        verifyRg(customer.getRg());
        verifyCpf(customer.getCpf());
        verifyEmail(customer.getEmail());
        verifyBirthDate(customer.getBirthDate());
        verifyAddress(customer.getAddress());
        return true;
    }

    public boolean notNull(CustomerDTO customer){
        if(customer.getName() != null &&
                customer.getLastName() != null &&
                customer.getEmail() != null &&
                customer.getSignUpDate() != null) return true;
        throw new InvalidRequestException("Customer validation failed. Some of the attributes is null or empty.");
    }

    public boolean verifyName(String name){
        if(name.length() <= 65) return true;
        throw new InvalidRequestException("Name validation failed. The name is too long (+65 characters)");
    }

    public boolean verifyLastName(String lastName){
        if(lastName.length() <= 65) return true;
        throw new InvalidRequestException("Last name validation failed. The last name is too long (+65 characters)");
    }

    public boolean verifyBirthDate(Date birthDate){

        int year = Integer.parseInt((birthDate.toString().split(" "))[5]);

        if(year > 1900 && year < 2008) return true;
        throw new InvalidRequestException("Birth date validation failed. The year must be between 1900 and 2008");

    }

    public boolean verifyRg(String rg){
        if (rg.matches(RG_REGEX_PATTERN)) return true;
        throw new InvalidRequestException("Rg validation failed. The rg pattern is invalid");

    }

    public boolean verifyCpf(String cpf){
        if (cpf.matches(CPF_REGEX_PATTERN)) return true;
        throw new InvalidRequestException("Cpf validation failed. The cpf pattern is invalid");
    }

    public boolean verifyEmail(String email){
        if(email.matches(EMAIL_REGEX_PATTERN)) return true;
        throw new InvalidRequestException("Email validation failed. The email pattern is invalid");
    }

    public boolean verifyAddress(AddressDTO address){
        AddressValidation validation = new AddressValidation();
        if (validation.validateRequest(address)) return true;
        throw new InvalidRequestException("Address validation failed");
    }

}
