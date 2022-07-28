package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.models.entities.PhoneEntity;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.PhoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.*;

@Slf4j
@Service
public class PhoneService {

    @Autowired
    PhoneRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    PhoneValidation validation = new PhoneValidation();

    public PhoneDTO create(PhoneDTO phone){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        log.info("[PROGRESS] Finding a customer in database betwen the id {}...", phone.getCustomerJsonId());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(phone.getCustomerJsonId());

        if (optionalCustomer.isEmpty()) {
            log.warn(CUSTOMER_NOT_FOUND_LOG);
            throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
        }

        if (validation.validateRequest(ValidationTypeEnum.CREATE, phone, repository)) {

            phone.setCustomer(modelMapper.mapper().map(optionalCustomer.get(), CustomerDTO.class));

            log.info(REQUEST_SUCCESSFULL);
            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(phone, PhoneEntity.class)), PhoneDTO.class);

        }

        log.warn(PHONE_VALIDATION_FAILED_LOG);
        throw new InvalidRequestException("Phone validation failed");

    }

    public List<PhoneDTO> findAll() {
        log.info(LOG_BAR);
        log.info("[STARTING] Starting findAll method");
        if(!repository.findAll().isEmpty()) {
            log.warn(REQUEST_SUCCESSFULL);
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, PhoneDTO.class))
                    .collect(Collectors.toList());
        }
        log.warn(PHONE_NOT_FOUND_LOG);
        throw new ObjectNotFoundException("There is no phones saved in the database");
    }

    public PhoneDTO findById(Long id) {
        log.info(LOG_BAR);
        log.info("[STARTING] Starting findById method");

        Optional<PhoneEntity> phone = repository.findById(id);
        return modelMapper.mapper().map(
                phone.orElseThrow(() -> new ObjectNotFoundException(PHONE_NOT_FOUND)), PhoneDTO.class);
    }

    public PhoneDTO update(Long id, PhoneDTO phone) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting update method");

        log.info("[PROGRESS] Searching for a phoneEntity by id {}...", id);
        Optional<PhoneEntity> phoneEntityOptional = repository.findById(id);

        log.info("[PROGRESS] Searching for a customer by id {}...", phone.getCustomerJsonId());
        Optional<CustomerEntity> phoneCustomerOptional = customerRepository.findById(phone.getCustomerJsonId());

        if (phoneCustomerOptional.isEmpty()) {
            log.warn(CUSTOMER_NOT_FOUND_LOG);
            throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
        }

        if (phoneEntityOptional.isPresent() && validation.validateRequest(ValidationTypeEnum.UPDATE, phone, repository)) {

            PhoneEntity phoneEntity = phoneEntityOptional.get();
            log.warn("[INFO] Phone found: ({}){}", phoneEntity.getPrefix(), phoneEntity.getNumber());

            log.info("[PROGRESS] Charging the phone with the updated attributes...");
            phoneEntity.setPhoneType(phone.getPhoneType());
            phoneEntity.setNumber(phone.getNumber());
            phoneEntity.setPrefix(phone.getPrefix());
            phoneEntity.setCustomer(phoneCustomerOptional.get());
            repository.save(phoneEntity);
            return phone;
        }

        log.warn(PHONE_NOT_FOUND_LOG);
        throw new InvalidRequestException(PHONE_NOT_FOUND);
    }

    public Boolean delete(Long id){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting delete by id method");

        log.info("[PROGRESS] Searching in database for a Phone with id {}...", id);
        if (repository.findById(id).isPresent()) {

            log.warn("[INFO] Phone found and removed from database.");
            repository.deleteById(id);

            log.warn(REQUEST_SUCCESSFULL);
            return true;

        }
        log.warn(PHONE_NOT_FOUND_LOG);
        throw new InvalidRequestException(PHONE_NOT_FOUND);

    }
}
