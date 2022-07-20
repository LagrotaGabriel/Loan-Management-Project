package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.entities.*;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.AddressValidation;
import br.com.loanapi.validations.CustomerValidation;
import br.com.loanapi.validations.PhoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.LOG_BAR;

@Slf4j
@Service
public class CustomerService {

    //TODO VERIFY THE LOGS AND USE CONSTANTS AT STRINGS

    @Autowired
    CustomerRepository repository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    AddressService addressService;

    String CUSTOMER_NOT_FOUND = "Customer not found";

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();
    AddressValidation addressValidation = new AddressValidation();
    PhoneValidation phoneValidation = new PhoneValidation();

    public CustomerDTO create(CustomerDTO customer) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        log.info("[PROGRESS] Starting customer, customer phone and customer address validations...");

        if (validation.validateRequest(customer, repository, phoneRepository)) {

            log.info("[PROGRESS] Verifying if the address already exists at database: {}", customer.getAddress());

            Optional<AddressEntity> addressEntity = addressRepository.findByStreetNumberAndPostalCode(
                            customer.getAddress().getStreet(),
                            customer.getAddress().getNumber(),
                            customer.getAddress().getPostalCode());

            AddressDTO addressDTO = new AddressDTO();

            if (addressEntity.isPresent()) {

                log.warn("[INFO] The passed address already exist");
                addressDTO = modelMapper.mapper().map(addressEntity.get(), AddressDTO.class);
                addressDTO.addCustomer(customer);
            }
            else {

                log.warn("[INFO] The passed address dont exist");
                addressDTO = customer.getAddress();
                addressDTO.addCustomer(customer);
            }

            log.info("[PROGRESS] Saving the customer at database...");
            addressRepository.save(modelMapper.mapper().map(addressDTO, AddressEntity.class));
            log.info("[SUCCESS] Request successfull");
            return customer;

        }

        log.info("[FAILURE] Customer validation failed");
        throw new InvalidRequestException("Customer validation failed");

    }

    public List<CustomerDTO> findAll(){
        if (!repository.findAll().isEmpty()) return repository.findAll()
                .stream().map(x -> modelMapper.mapper().map(x, CustomerDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no consumers saved in the database");
    }

    public CustomerDTO findById(Long id){
        Optional<CustomerEntity> customer = repository.findById(id);
        return modelMapper.mapper().map(
                customer.orElseThrow(() -> new ObjectNotFoundException(CUSTOMER_NOT_FOUND)), CustomerDTO.class);
    }

    public CustomerDTO update(Long id, CustomerDTO customer){

        if (validation.validateRequest(customer, repository, phoneRepository)) {
            CustomerDTO customerDTO = findById(id);

            customerDTO.setName(customer.getName());
            customerDTO.setLastName(customer.getName());
            customerDTO.setBirthDate(customer.getBirthDate());
            customerDTO.setSignUpDate(customer.getSignUpDate());
            customerDTO.setRg(customer.getRg());
            customerDTO.setCpf(customer.getCpf());
            customerDTO.setEmail(customer.getEmail());

            customerDTO.setAddress(customer.getAddress());
            customerDTO.setScore(customer.getScore());

            customerDTO.setPhones(customer.getPhones());
            customerDTO.setLoans(customer.getLoans());

            return modelMapper.mapper().map(
                    repository.save(modelMapper.mapper().map(customerDTO, CustomerEntity.class)), CustomerDTO.class);
        }
        else{
            throw new InvalidRequestException("Customer validation failed");
        }
    }

    public Boolean deleteById(Long id) {
        CustomerEntity customer = modelMapper.mapper().map(findById(id), CustomerEntity.class);
        repository.delete(customer);
        return true;
    }

}
