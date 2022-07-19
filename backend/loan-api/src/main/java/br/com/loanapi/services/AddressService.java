package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.validations.AddressValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.*;

@Service
@Slf4j
public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    AddressValidation validation = new AddressValidation();

    public AddressDTO create(AddressDTO address){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        if(validation.validateRequest(address)) {

            return null;

        }

        log.error("[FAILURE]  " + ADDRESS_VALIDATION_FAILED);
        throw new InvalidRequestException(ADDRESS_VALIDATION_FAILED);

    }

    public List<AddressDTO> findAll(){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findAll method...");
        log.info("[PROGRESS] Verifying if there is addresses saved in the database...");
        if(!repository.findAll().isEmpty()) {
            log.info(REQUEST_SUCCESSFULL);
            return repository.findAll().stream().map(
                    x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList());
        }
        log.error("[FAILURE]  There is no addresses saved in the database");
        throw new ObjectNotFoundException("There is no addresses saved in the database");
    }

    public AddressDTO findById(Long id){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findById method...");

        log.info("[PROGRESS] Searching for a address by id {}...", id);
        Optional<AddressEntity> address = repository.findById(id);

        address.ifPresent(addressEntity -> log.info(REQUEST_SUCCESSFULL));
        if(address.isEmpty()) log.error("[FAILURE]  Address with id {} not found", id);
        return modelMapper.mapper().map(
                address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){

        return null;

    }

    public Boolean deleteById(Long id) {

        return null;

    }

}
