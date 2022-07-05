package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.CityDTO;

public class CityValidation {

    public boolean validateRequest(CityDTO city){
        notNull(city);
        verifyCity(city.getCity());
        return true;
    }

    public boolean notNull(CityDTO city){
        if(city.getCity() != null && city.getState() != null) return true;
        throw new InvalidRequestException("City validation failed. Some of the attributes is null or empty.");
    }

    public boolean verifyCity(String city){
        if(city.length() <= 65) return true;
        throw new InvalidRequestException("City validation failed. The city name is too long (+65 characters).");
    }

}
