package br.com.loanapi.dao.impl;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.CustomerDAO;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.entities.*;
import br.com.loanapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    CustomerRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String CUSTOMER_NOT_FOUND = "Customer not found";

    @Override
    public CustomerEntity create(CustomerDTO customer) {
        return repository.save(modelMapper.mapper().map(customer, CustomerEntity.class));
    }

    @Override
    public List<CustomerEntity> findAll() {
        return repository.findAll().stream()
                .map(x -> modelMapper.mapper().map(x, CustomerEntity.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerEntity findById(Long id) {
        Optional<CustomerEntity> customer = repository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException(CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerEntity update(Long id, CustomerDTO customer) {

        CustomerEntity customerEntity = findById(id);

        customerEntity.setName(customer.getName());
        customerEntity.setLastName(customer.getName());
        customerEntity.setBirthDate(customer.getBirthDate());
        customerEntity.setSignUpDate(customer.getSignUpDate());
        customerEntity.setRg(customer.getRg());
        customerEntity.setCpf(customer.getCpf());
        customerEntity.setEmail(customer.getEmail());

        customerEntity.setAddress(modelMapper.mapper().map(customer.getAddress(), AddressEntity.class));
        customerEntity.setScore(modelMapper.mapper().map(customer.getScore(), ScoreEntity.class));

        customerEntity.setPhones(customer.getPhones()
                .stream().map(x -> modelMapper.mapper().map(x, PhoneEntity.class)).collect(Collectors.toList()));
        customerEntity.setLoans(customer.getLoans()
                .stream().map(x -> modelMapper.mapper().map(x, LoanEntity.class)).collect(Collectors.toList()));

        return repository.save(customerEntity);
    }

    @Override
    public Boolean deleteById(Long id) {
        CustomerEntity customer = findById(id);
        repository.delete(customer);
        return true;
    }
}
