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

    //TODO VERIFY THE LOGS AND USE CONSTANTS AT STRINGS

    @Autowired
    CustomerRepository repository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();
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
                customer.setPhoneList(customer.getPhones());
                addressDTO.addCustomer(customer);
            }
            else {

                log.warn("[INFO] The passed address dont exist");
                addressDTO = customer.getAddress();
                customer.setPhoneList(customer.getPhones());
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


    //TODO Diminuir complexidade do código
    public CustomerDTO update(Long id, CustomerDTO customer){

        // Procurando customer pelo id passado
        Optional<CustomerEntity> customerOptional = repository.findById(id);
        CustomerEntity customerEntityUpdated = new CustomerEntity();
        AddressEntity addressUpdated;

        customerEntityUpdated.setName(customer.getName());
        customerEntityUpdated.setLastName(customer.getLastName());
        customerEntityUpdated.setCpf(customer.getCpf());
        customerEntityUpdated.setRg(customer.getRg());
        customerEntityUpdated.setEmail(customer.getEmail());
        customerEntityUpdated.setBirthDate(customer.getBirthDate());

        // Se um customer for encontrado pelo id passado
        if (customerOptional.isPresent()) {

            // Atribuindo o optional a um objeto do tipo Entity real
            CustomerEntity customerEntity = customerOptional.get();

            // Verificando se o request body está tudo ok através da classe de validation
            if (validation.validateRequest(ValidationTypeEnum.UPDATE, customer, null, phoneRepository)) {

                // Verificando se o endereço passado já existe no banco de dados
                Optional<AddressEntity> addressEntity = addressRepository.findByStreetNumberAndPostalCode(
                        customer.getAddress().getStreet(),
                        customer.getAddress().getNumber(),
                        customer.getAddress().getPostalCode());

                // Se o endereço passado existir
                if (addressEntity.isPresent()) {

                    // Atribuindo o optional ao dto
                    addressUpdated = modelMapper.mapper().map(addressEntity.get(), AddressEntity.class);

                    customerEntityUpdated.setId(customerEntity.getId());
                    customerEntityUpdated.setAddress(addressUpdated);
                    customerEntityUpdated.setPhones(customerEntity.getPhones());

                    // SE O TELEFONE PASSADO NO UPDATE FOR DIFERENTE DO QUE JÁ EXISTE
                    if (!customer.getPhones().equals(customerEntity.getPhones().stream().map(x -> modelMapper.mapper().map(x, PhoneDTO.class)).toList())) {

                        // FOR RODANDO LISTA DE PHONES PASSADAS NO JSON
                        for (PhoneDTO phone : customer.getPhones()) {
                            // SE O PHONE PASSADO NO JSON NÃO EXISTIR
                            if (phoneValidation.exists(ValidationTypeEnum.UPDATE, phone, phoneRepository)) {
                                // ADICIONA TELEFONE A LISTA DE TELEFONES DO CUSTOMER
                                customerEntityUpdated.addPhone(modelMapper.mapper().map(phone, PhoneEntity.class));
                            }
                        }
                    }

                    customerEntityUpdated.setAddress(addressUpdated);
                    addressUpdated.getCustomers().set(addressUpdated.getCustomers().indexOf(customerEntity), customerEntityUpdated);

                }

                // SE O ENDEREÇO PASSADO NÃO EXISTIR
                else {

                    // Pegando o endereço antigo do cliente
                    Optional<AddressEntity> addressEntityOptional = addressRepository.findByStreetNumberAndPostalCode(
                                            customerEntity.getAddress().getStreet(),
                                            customerEntity.getAddress().getNumber(),
                                            customerEntity.getAddress().getPostalCode());

                    // Se o antigo endereço do cliente for encontrado
                    if (addressEntityOptional.isPresent()) {

                        // Passando endereço antigo do cliente para variável do tipo Entity
                        AddressEntity oldAddressEntity = addressEntityOptional.get();
                        // Removendo customer do endereço antigo do cliente
                        oldAddressEntity.getCustomers().remove(customerEntity);
                        // Removendo o customer do banco de dados
                        repository.delete(customerEntity);
                        // Persistindo endereço antigo do cliente atualizado sem o customer
                        addressRepository.save(oldAddressEntity);
                    }

                    addressUpdated = modelMapper.mapper().map(customer.getAddress(), AddressEntity.class);

                    customerEntityUpdated.setId(null);
                    customerEntityUpdated.setAddress(addressUpdated);
                    customerEntityUpdated.setPhones(customerEntity.getPhones());

                    // SE O TELEFONE PASSADO NO UPDATE FOR DIFERENTE DO QUE JÁ EXISTE
                    if (!customer.getPhones().equals(customerEntity.getPhones().stream().map(x -> modelMapper.mapper().map(x, PhoneDTO.class)).toList())) {

                        // FOR RODANDO LISTA DE PHONES PASSADAS NO JSON
                        for (PhoneDTO phone : customer.getPhones()) {
                            // SE O PHONE PASSADO NO JSON NÃO EXISTIR
                            if (phoneValidation.exists(ValidationTypeEnum.UPDATE, phone, phoneRepository)) {
                                // ADICIONA TELEFONE A LISTA DE TELEFONES DO CUSTOMER
                                customerEntityUpdated.addPhone(modelMapper.mapper().map(phone, PhoneEntity.class));
                            }
                        }
                    }

                    // RETIRANDO O ID DOS TELEFONES E SETANDO O CLIENTE NELES
                    for (PhoneEntity phone: customerEntityUpdated.getPhones()) {
                        phone.setId(null);
                        phone.setCustomer(customerEntityUpdated);
                    }

                    customerEntityUpdated.setAddress(addressUpdated);
                    addressUpdated.getCustomers().add(customerEntityUpdated);

                }

                addressRepository.save(addressUpdated);
                return modelMapper.mapper().map(customerEntityUpdated, CustomerDTO.class);

            }

            // Se a validation tiver algo de errado
            throw new InvalidRequestException("Customer validation failed");
        }
        // Se nenhum customer for encontrado pelo id passado
        throw new ObjectNotFoundException(CUSTOMER_NOT_FOUND);
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
            log.info("[PROGRESS] Saving the address updated without the deleted customer at the list...");
            addressRepository.save(optionalAddress.get());

            log.warn(REQUEST_SUCCESSFULL);
            return true;

        }
        log.error("[FAILURE] Customer with id {} not found", id);
        throw new ObjectNotFoundException(CUSTOMER_NOT_FOUND);

    }

}
