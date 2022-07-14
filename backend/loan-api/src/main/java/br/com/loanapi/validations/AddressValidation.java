package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.AddressDTO;
import lombok.extern.slf4j.Slf4j;

import static br.com.loanapi.utils.RegexPatterns.POSTAL_CODE_REGEX_PATTERN;
import static br.com.loanapi.utils.RegexPatterns.STREET_NUMBER_REGEX_PATTERN;

@Slf4j
public class AddressValidation {

    public boolean validateRequest(AddressDTO address){

        log.info("[STARTING] Starting address validation");

        notNull(address);
        verifyStreet(address.getStreet());
        verifyNeighborhood(address.getNeighborhood());
        verifyNumber(address.getNumber());
        verifyPostalCode(address.getPostalCode());

        log.info("[SUCCESS]  Validation successfull");
        return true;
    }

    public boolean notNull(AddressDTO address){

        log.info("[PROGRESS] Validating if the object have null attributes...");
        if(address.getStreet() != null &&
                address.getNeighborhood() != null &&
                address.getNumber() != null &&
                address.getPostalCode() != null &&
                address.getCity() != null) return true;

        log.error("[FAILURE] Address validation failed. Some of the attributes are null or empty");
        throw new InvalidRequestException("Address validation failed. Some of the attributes is null or empty.");
    }

    public boolean verifyStreet(String street){

        log.info("[PROGRESS] Validating street name...");
        if(street.length() <= 65) return true;

        log.error("[FAILURE] Address validation failed. The street name is too long (+65 characters)");
        throw new InvalidRequestException("Address validation failed. The street name is too long (+65 characters).");
    }

    public boolean verifyNeighborhood(String neighborhood){

        log.info("[PROGRESS] Validating neighborhood name...");
        if(neighborhood.length() <= 65) return true;

        log.error("[FAILURE] Address validation failed. The neighborhood name is too long (+65 characters)");
        throw new InvalidRequestException("Address validation failed. The neighborhood name is too long (+65 characters).");
    }

    public boolean verifyNumber(Integer number){

        log.info("[PROGRESS] Validating street number...");
        if(number.toString().matches(STREET_NUMBER_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Address validation failed. The number must have only numbers with the max size of 5 characters");
        throw new InvalidRequestException("Address validation failed. " +
                "The number must have only numbers with the max size of 5 characters.");
    }

    public boolean verifyPostalCode(String postalCode){

        log.info("[PROGRESS] Validating postal code...");
        if(postalCode.matches(POSTAL_CODE_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Address validation failed. The postal code should follow the pattern xxxxx-xxx with only numbers");
        throw new InvalidRequestException("Address validation failed. The postal code should follow the pattern " +
                "xxxxx-xxx, with only numbers");
    }

}
