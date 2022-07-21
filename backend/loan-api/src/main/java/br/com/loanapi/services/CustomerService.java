package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.entities.*;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.CustomerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    String CUSTOMER_NOT_FOUND = "Customer not found";

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();

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

        // Procurando customer pelo id passado
        Optional<CustomerEntity> customerOptional = repository.findById(id);

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

                AddressDTO addressDTO = customer.getAddress();

                // Se o endereço passado existir
                if (addressEntity.isPresent()) {

                    // Atribuindo o optional ao dto
                    AddressEntity addressUpdated = modelMapper.mapper().map(addressEntity.get(), AddressEntity.class);

                    CustomerEntity customerEntityUpdated = new CustomerEntity();

                    customerEntityUpdated.setId(customerEntity.getId());
                    customerEntityUpdated.setName(customer.getName());
                    customerEntityUpdated.setLastName(customer.getLastName());
                    customerEntityUpdated.setCpf(customer.getCpf());
                    customerEntityUpdated.setRg(customer.getRg());
                    customerEntityUpdated.setEmail(customer.getEmail());
                    customerEntityUpdated.setBirthDate(customer.getBirthDate());
                    customerEntityUpdated.setAddress(addressUpdated);
                    customerEntityUpdated.setPhones(customer.getPhones()
                            .stream().map(x -> modelMapper.mapper().map(x, PhoneEntity.class)).collect(Collectors.toList()));

                    addressUpdated.getCustomers().set(addressUpdated.getCustomers().indexOf(customerEntity), customerEntityUpdated);

                    addressRepository.save(addressUpdated);
                }

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

                    // Atualizando objeto já persistido com os atributos do JSON
                    customerEntity.setId(null);
                    customerEntity.setName(customer.getName());
                    customerEntity.setLastName(customer.getLastName());
                    customerEntity.setCpf(customer.getCpf());
                    customerEntity.setRg(customer.getRg());
                    customerEntity.setEmail(customer.getEmail());
                    customerEntity.setBirthDate(customer.getBirthDate());
                    customerEntity.setAddress(modelMapper.mapper().map(customer.getAddress(), AddressEntity.class));
                    customerEntity.setPhones(customer.getPhones()
                            .stream().map(x -> modelMapper.mapper().map(x, PhoneEntity.class)).collect(Collectors.toList()));

                    // Criando o customer no address
                    addressDTO.addCustomer(modelMapper.mapper().map(customerEntity, CustomerDTO.class));

                    addressRepository.save(modelMapper.mapper().map(addressDTO, AddressEntity.class));
                }

                // Salvando o endereço no banco de dados em cascata
                return customer;

            }

            // Se a validation tiver algo de errado
            throw new InvalidRequestException("Customer validation failed");
        }
        // Se nenhum customer for encontrado pelo id passado
        throw new ObjectNotFoundException("Customer not found");
    }

    public Boolean deleteById(Long id) {
        CustomerEntity customer = modelMapper.mapper().map(findById(id), CustomerEntity.class);
        repository.delete(customer);
        return true;
    }

}
