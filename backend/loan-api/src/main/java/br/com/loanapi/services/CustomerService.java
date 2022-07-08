package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.entities.*;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.validations.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    String CUSTOMER_NOT_FOUND = "Customer not found";

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();

    public CustomerDTO create(CustomerDTO customer){
        if (validation.validateRequest(customer))
            return modelMapper.mapper().map(
                    repository.save(modelMapper.mapper().map(customer, CustomerEntity.class)), CustomerDTO.class);
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
        //TODO Verify if nested objects come with Entity type. If comes, change to DTO type and ADD the cascade to list
        if (validation.validateRequest(customer)) {
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
