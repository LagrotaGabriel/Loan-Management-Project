package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.repositories.CityRepository;
import br.com.loanapi.validations.CityValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.*;

@Slf4j
@Service
public class CityService {

    @Autowired
    CityRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    CityValidation validation = new CityValidation();

    public CityDTO create(CityDTO city){
        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");
        if(validation.validateRequest(city, repository)) {
            log.info(REQUEST_SUCCESSFULL);
            return modelMapper.mapper().map(
                    repository.save(modelMapper.mapper().map(city, CityEntity.class)), CityDTO.class);
        }
        log.info("[FAILURE] " + CITY_VALIDATION_FAILED);
        throw new InvalidRequestException(CITY_VALIDATION_FAILED);
    }

    public List<CityDTO> findAll(){
        log.info(LOG_BAR);
        log.info("[STARTING] Starting findAll method");
        log.info("[PROGRESS] Verifying if there is cities saved in the database...");
        if(!repository.findAll().isEmpty()) {
            log.info(REQUEST_SUCCESSFULL);
            return repository.findAll().stream().map(
                    x -> modelMapper.mapper().map(x, CityDTO.class)).collect(Collectors.toList());
        }
        log.error("[FAILURE]  There is no cities saved in the database");
        throw new ObjectNotFoundException("There is no cities saved in the database");
    }

    public CityDTO findById(Long id){
        log.info(LOG_BAR);
        log.info("[STARTING] Starting findById method...");

        log.info("[PROGRESS] Searching for a city by id {}...", id);
        Optional<CityEntity> city = repository.findById(id);

        city.ifPresent(addressEntity -> log.info(REQUEST_SUCCESSFULL));
        if(city.isEmpty()) log.error("[FAILURE]  City with id {} not found", id);
        return modelMapper.mapper().map(
                city.orElseThrow(() -> new ObjectNotFoundException(CITY_NOT_FOUND)), CityDTO.class);
    }

    public CityDTO update(Long id, CityDTO city){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting update method");

        log.info("[PROGRESS] Searching for a city by id {}...", id);
        Optional<CityEntity> cityEntityOptional = repository.findById(id);

        if(cityEntityOptional.isPresent()) {

            log.info("[INFO] City finded: {}, {}", cityEntityOptional.get().getState(), cityEntityOptional.get().getCity());

            if (validation.validateRequest(city, repository)) {

                CityDTO dto = modelMapper.mapper().map(cityEntityOptional, CityDTO.class);

                log.info("[PROGRESS] Updating the finded city with the JSON body content...");
                dto.setId(id);
                dto.setCity(city.getCity());
                dto.setState(city.getState());
                if(cityEntityOptional.get().getAddresses() != null)
                    dto.setAddresses(cityEntityOptional.get().getAddresses().stream().map(
                            x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList()));


                log.info(REQUEST_SUCCESSFULL);
                return modelMapper.mapper().map(repository.save(
                        modelMapper.mapper().map(dto, CityEntity.class)), CityDTO.class);
            }
            else {
                log.error("[FAILURE] City validation failed");
                throw new InvalidRequestException(CITY_VALIDATION_FAILED);
            }

        }

        else{
            log.error("[FAILURE] City not found at database");
            throw new ObjectNotFoundException(CITY_NOT_FOUND);
        }

    }

    public Boolean deleteById(Long id) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting deleteById method...");

        log.info("[PROGRESS] Searching a City by id {}...", id);
        if (repository.findById(id).isPresent()) {

            log.warn("[INFO] City finded");

            log.info(REQUEST_SUCCESSFULL);
            repository.deleteById(id);
            return true;
        }
        else{
            log.error("[FAILURE] City not found");
            throw new ObjectNotFoundException(CITY_NOT_FOUND);
        }
    }

}
