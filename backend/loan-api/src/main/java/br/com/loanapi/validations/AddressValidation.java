package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.AddressDTO;

public class AddressValidation {

    public boolean validateRequest(AddressDTO address){
        notNull(address);
        verifyStreet(address.getStreet());
        verifyNeighborhood(address.getNeighborhood());
        verifyNumber(address.getNumber());
        verifyPostalCode(address.getPostalCode());
        return true;
    }

    public boolean notNull(AddressDTO address){
        if(address.getStreet() != null &&
                address.getNeighborhood() != null &&
                address.getNumber() != null &&
                address.getPostalCode() != null &&
                address.getCity() != null) return true;

        throw new InvalidRequestException("Address validation failed. Some of the attributes is null or empty.");
    }

    public boolean verifyStreet(String street){
        if(street.length() <= 65) return true;
        throw new InvalidRequestException("Address validation failed. The street name is too long (+65 characters).");
    }

    public boolean verifyNeighborhood(String neighborhood){
        if(neighborhood.length() <= 65) return true;
        throw new InvalidRequestException("Address validation failed. The neighborhood name is too long (+65 characters).");
    }

    public boolean verifyNumber(Integer number){
        if(number.toString().matches("[0-9]{1,5}")) return true;
        throw new InvalidRequestException("Address validation failed. " +
                "The number must have only numbers with the max size of 5 characters.");
    }

    public boolean verifyPostalCode(String postalCode){
        if(postalCode.matches("[0-9]{5}-[0-9]{3}")) return true;
        throw new InvalidRequestException("Address validation failed. The postal code should follow the pattern " +
                "xxxxx-xxx, with only numbers");
    }

}
