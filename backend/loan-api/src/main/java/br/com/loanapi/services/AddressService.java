package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CustomerEntity;
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

        if(validation.validateRequest(address, repository)) {
            log.warn("[INFO] Address created at database: {}", address.getStreet() + ", " + address.getNumber());

            log.warn(REQUEST_SUCCESSFULL);
            return modelMapper.mapper().map(repository
                    .save(modelMapper.mapper().map(address, AddressEntity.class)), AddressDTO.class);
        }

        log.error(ADDRESS_VALIDATION_FAILED_LOG);
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

        log.info(LOG_BAR);
        log.info("[STARTING] Starting update method");

        Optional<AddressEntity> addressOptional = repository.findById(id);

        if (address.getCustomers() == null) {
            List<CustomerDTO> customers = new ArrayList<>();
            address.setCustomers(customers);
        }

        log.info("[PROGRESS] Verifying if the passed id brings a database address...");
        if (addressOptional.isPresent()) {

            log.warn("[INFO] Address finded: {}, {}", address.getStreet(), address.getNumber());

            AddressEntity addressEntity = addressOptional.get();

            if (validation.validateRequest(address, repository)) {

                log.info("[PROGRESS] Setting the new attributes values to persisted address...");
                addressEntity.setStreet(address.getStreet());
                addressEntity.setNumber(address.getNumber());
                addressEntity.setNeighborhood(address.getNeighborhood());
                addressEntity.setCity(address.getCity());
                addressEntity.setState(address.getState());
                addressEntity.setPostalCode(address.getPostalCode());
                addressEntity.setCustomers(address.getCustomers().stream().map(x -> modelMapper.mapper().map(x, CustomerEntity.class)).collect(Collectors.toList()));

                log.warn("[INFO] Address successfully updated at database");
                log.warn(REQUEST_SUCCESSFULL);
                return modelMapper.mapper().map(repository.save(addressEntity), AddressDTO.class);

            }

            log.error(ADDRESS_VALIDATION_FAILED_LOG);
            throw new InvalidRequestException(ADDRESS_VALIDATION_FAILED);

        }

        log.error(ADDRESS_NOT_FOUND_LOG);
        throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);
    }

    public Boolean deleteById(Long id) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting deleteById method...");

        log.info("[PROGRESS] Searching for a address by id {}...", id);
        Optional<AddressEntity> address = repository.findById(id);

        if (address.isPresent()) {
            log.warn("[PROGRESS] Address finded. Removing...");
            repository.deleteById(id);
            log.warn(REQUEST_SUCCESSFULL);
            return true;
        }
        log.error("[FAILURE]  Address with id {} not found", id);
        throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);

    }

}
