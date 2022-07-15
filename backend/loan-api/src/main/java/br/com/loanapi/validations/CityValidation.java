package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.repositories.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityValidation {

    public boolean validateRequest(CityDTO city, CityRepository repository){

        log.info("[STARTING] Starting city validation");

        exists(city, repository);
        notNull(city);
        verifyCity(city.getCity());

        log.info("[SUCCESS]  Validation successfull");
        return true;
    }

    public boolean exists(CityDTO city, CityRepository repository){
        log.info("[PROGRESS] Validating if the object already exists in database...");
        if (repository.findByCityAndState(city.getCity(), city.getState()).isEmpty()) return true;
        log.info("[FAILURE] The passed city already exists in database");
        throw new InvalidRequestException("City validation failed. The request body city already exists in database");
    }

    public boolean notNull(CityDTO city){
        log.info("[PROGRESS] Validating if the object have null attributes...");
        if(city.getCity() != null && city.getState() != null) return true;

        log.error("[FAILURE] City validation failed. Some of the attributes are null or empty");
        throw new InvalidRequestException("City validation failed. Some of the attributes is null or empty.");
    }

    public boolean verifyCity(String city){
        log.info("[PROGRESS] Validating city name...");
        if(city.length() <= 65) return true;

        log.info("[FAILURE] City validation failed. The city name is too long (+65 characters)");
        throw new InvalidRequestException("City validation failed. The city name is too long (+65 characters).");
    }

}
