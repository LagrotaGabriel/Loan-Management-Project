package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;

import static br.com.loanapi.utils.RegexPatterns.POSTAL_CODE_REGEX_PATTERN;
import static br.com.loanapi.utils.RegexPatterns.STREET_NUMBER_REGEX_PATTERN;

@Slf4j
public class AddressValidation {

    public boolean validateRequest(ValidationTypeEnum validationType, AddressDTO address, AddressRepository repository){

        log.info("[STARTING] Starting address validation");

        notNull(address);
        if (validationType == ValidationTypeEnum.CREATE) {
            exists(address, repository);
        }
        verifyStreet(address.getStreet());
        verifyNeighborhood(address.getNeighborhood());
        verifyNumber(address.getNumber());
        verifyPostalCode(address.getPostalCode());
        verifyCity(address.getCity());

        log.info("[SUCCESS]  Validation successfull");
        return true;
    }

    public boolean exists(AddressDTO address, AddressRepository repository) {

        log.info("[PROGRESS] Validating if the object already exists in database...");
        if (repository.findByStreetNumberAndPostalCode(address.getStreet(), address.getNumber(), address.getPostalCode()).isEmpty()) return true;

        log.error("[FAILURE] Address validation failed. The address already exists in database");
        throw new InvalidRequestException("Address validation failed. The address already exists in database");

    }

    public boolean notNull(AddressDTO address) {

        log.info("[PROGRESS] Validating if the object have null attributes...");
        if(address.getStreet() != null &&
                address.getNeighborhood() != null &&
                address.getNumber() != null &&
                address.getPostalCode() != null &&
                address.getCity() != null &&
                address.getState() != null) return true;

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

    public boolean verifyNumber(Integer number) {

        log.info("[PROGRESS] Validating street number...");
        if(number.toString().matches(STREET_NUMBER_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Address validation failed. The number must have only numbers with the max size of 5 characters");
        throw new InvalidRequestException("Address validation failed. " +
                "The number must have only numbers with the max size of 5 characters.");
    }

    public boolean verifyPostalCode(String postalCode) {

        log.info("[PROGRESS] Validating postal code...");
        if(postalCode.matches(POSTAL_CODE_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Address validation failed. The postal code should follow the pattern xxxxx-xxx with only numbers");
        throw new InvalidRequestException("Address validation failed. The postal code should follow the pattern " +
                "xxxxx-xxx, with only numbers");
    }

    public boolean verifyCity(String city) {

        log.info("[PROGRESS] Validating city...");
        if (city.length() <= 65) return true;

        log.error("[FAILURE] Address validation failed. The city name is too long (+65 characters)");
        throw new InvalidRequestException("Address validation failed. The city name is too long (+65 characters)");

    }

}
