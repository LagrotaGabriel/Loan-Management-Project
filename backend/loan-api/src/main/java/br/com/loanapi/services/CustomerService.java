package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.entities.*;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.AddressValidation;
import br.com.loanapi.validations.CustomerValidation;
import br.com.loanapi.validations.PhoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.RegexPatterns.CUSTOMER_NOT_FOUND;
import static br.com.loanapi.utils.StringConstants.*;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();
    AddressValidation addressValidation = new AddressValidation();
    PhoneValidation phoneValidation = new PhoneValidation();

    public CustomerDTO create(CustomerDTO customer) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        log.info("[PROGRESS] Starting customer, customer phone and customer address validations...");

        if (validation.validateRequest(ValidationTypeEnum.CREATE, customer, repository, phoneRepository)) {

            log.info("[PROGRESS] Verifying if the address already exists at database: {}", customer.getAddress());

            Optional<AddressEntity> addressEntity = addressRepository.findByStreetNumberAndPostalCode(
                    customer.getAddress().getStreet(),
                    customer.getAddress().getNumber(),
                    customer.getAddress().getPostalCode());

            AddressDTO addressDTO;

            if (addressEntity.isPresent()) {
                log.warn("[INFO] The passed address already exist");
                addressDTO = modelMapper.mapper().map(addressEntity.get(), AddressDTO.class);
            } else {

                log.warn("[INFO] The passed address dont exist");
                addressDTO = customer.getAddress();
            }

            customer.setPhoneList(customer.getPhones());
            addressDTO.addCustomer(customer);

            log.info("[PROGRESS] Saving the customer at database...");
            addressRepository.save(modelMapper.mapper().map(addressDTO, AddressEntity.class));
            log.info("[SUCCESS] Request successfull");
            return customer;

        }

        log.info(CUSTOMER_VALIDATION_FAILED_LOG);
        throw new InvalidRequestException(CUSTOMER_VALIDATION_FAILED);

    }

    public List<CustomerDTO> findAll() {
        if (!repository.findAll().isEmpty()) return repository.findAll()
                .stream().map(x -> modelMapper.mapper().map(x, CustomerDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no consumers saved in the database");
    }

    public CustomerDTO findById(Long id) {
        Optional<CustomerEntity> customer = repository.findById(id);
        return modelMapper.mapper().map(
                customer.orElseThrow(() -> new ObjectNotFoundException(CUSTOMER_NOT_FOUND)), CustomerDTO.class);
    }

    public CustomerDTO update(Long id, CustomerDTO customer) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting update method");

        log.info("[PROGRESS] Creating the customer method variables...");
        Optional<CustomerEntity> optionalCustomer = repository.findById(id);
        CustomerEntity findedCustomer;

        log.info("[PROGRESS] Creating the address method variables...");
        Optional<AddressEntity> optionalAddress = addressRepository.findByStreetNumberAndPostalCode(
                customer.getAddress().getStreet(),
                customer.getAddress().getNumber(),
                customer.getAddress().getPostalCode());
        AddressEntity findedAddress;

        log.info("[PROGRESS] Creating the updated objects variables...");
        AddressEntity updatedAddress;
        CustomerEntity updatedCustomer;

        log.info("[PROGRESS] Verifying if a customer with the id {} exists...", id);
        if (validation.validateRequest(ValidationTypeEnum.UPDATE, customer, repository, phoneRepository)
                && optionalCustomer.isPresent()
                && addressValidation.validateRequest(ValidationTypeEnum.UPDATE, customer.getAddress(), addressRepository)) {

            findedCustomer = optionalCustomer.get();
            log.warn("[INFO] Customer found: {} {}", findedCustomer.getName(), findedCustomer.getLastName());

            log.info("[PROGRESS] Updating the updatedCustomer with the JSON values...");
            updatedCustomer = findedCustomer;
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setBirthDate(customer.getBirthDate());
            updatedCustomer.setRg(customer.getRg());
            updatedCustomer.setCpf(customer.getCpf());
            for (PhoneDTO phone : customer.getPhones()) {
                if (phoneValidation.validateRequest(ValidationTypeEnum.CREATE, phone, phoneRepository)) {
                    updatedCustomer.addPhone(modelMapper.mapper().map(phone, PhoneEntity.class));
                }
            }

            log.info("[PROGRESS] Verifying if the passed JSON Address already exist at database...");
            if (optionalAddress.isPresent()) {

                findedAddress = optionalAddress.get();
                log.info("[INFO] Address found: {}, {}", findedAddress.getStreet(), findedAddress.getNumber());

                log.info("[PROGRESS] Verifying if the JSON address is different of the older customer address...");
                if (findedCustomer.getAddress() != findedAddress) {

                    log.warn("[INFO] The JSON address is different.");
                    log.info("[PROGRESS] Removing the customer of the old address...");
                    AddressEntity oldAddress = findedCustomer.getAddress();
                    oldAddress.removeCustomer(findedCustomer);

                    log.info("[PROGRESS] Saving the old address without the customer...");
                    addressRepository.save(oldAddress);

                    log.info("[PROGRESS] Setting the updatedAddress value with the finded address value...");
                    updatedAddress = modelMapper.mapper().map(findedAddress, AddressEntity.class);

                } else {
                    log.warn("[INFO] The JSON address is equals than the older customer address.");
                    log.info("[PROGRESS] Setting the updatedAddress value to the JSON address value...");
                    updatedAddress = findedAddress;
                }

            } else {

                log.warn("[INFO] Address not found.");

                log.info("[PROGRESS] Removing the customer of the old address...");
                AddressEntity oldAddress = findedCustomer.getAddress();
                oldAddress.removeCustomer(findedCustomer);

                log.info("[PROGRESS] Saving the old address without the customer...");
                addressRepository.save(oldAddress);

                log.info("[PROGRESS] Setting the updatedAddress value to the JSON address value...");
                updatedAddress = modelMapper.mapper().map(customer.getAddress(), AddressEntity.class);

            }

            log.info("[PROGRESS] Adding the address to customer and the customer to address...");
            updatedAddress.addCustomer(updatedCustomer);

            log.info("[PROGRESS] Saving the new address with the updated customer inside...");
            addressRepository.save(updatedAddress);

            log.warn(REQUEST_SUCCESSFULL);
            return modelMapper.mapper().map(updatedCustomer, CustomerDTO.class);
        }

        log.info(CUSTOMER_NOT_FOUND_LOG);
        throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
    }

    public Boolean deleteById(Long id) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting deleteById method...");

        log.info("[PROGRESS] Searching for a customer by id {}...", id);
        Optional<CustomerEntity> optionalCustomer = repository.findById(id);

        if (optionalCustomer.isPresent()) {

            log.warn("[INFO] Customer found.");

            log.info("[PROGRESS] Searching for the customer address at database...");
            Optional<AddressEntity> optionalAddress = addressRepository.findByStreetNumberAndPostalCode(
                    optionalCustomer.get().getAddress().getStreet(),
                    optionalCustomer.get().getAddress().getNumber(),
                    optionalCustomer.get().getAddress().getPostalCode());

            log.info("[PROGRESS] Removing the customer of the database...");
            repository.deleteById(id);
            log.info("[PROGRESS] Removing the customer of the adress customers list...");
            optionalAddress.ifPresent(addressEntity -> addressEntity.getCustomers().remove(optionalCustomer.get()));
            if (optionalAddress.isPresent()) {
                log.info("[PROGRESS] Saving the address updated without the deleted customer at the list...");
                addressRepository.save(optionalAddress.get());
            }

            log.warn(REQUEST_SUCCESSFULL);
            return true;

        }
        log.error("[FAILURE] Customer with id {} not found", id);
        throw new ObjectNotFoundException(CUSTOMER_NOT_FOUND);

    }

}
