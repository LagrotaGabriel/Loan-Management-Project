package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.CustomerDAO;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.validations.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO dao;

    @Autowired
    ModelMapperConfig modelMapper;

    CustomerValidation validation = new CustomerValidation();

    public CustomerDTO create(CustomerDTO customer){
        if (validation.validateRequest(customer)) return modelMapper.mapper().map(dao.create(customer), CustomerDTO.class);
        throw new InvalidRequestException("Customer validation failed");
    }

    public List<CustomerDTO> findAll(){
        if (!dao.findAll().isEmpty()) return dao.findAll()
                .stream().map(x -> modelMapper.mapper().map(x, CustomerDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no consumers saved in the database");
    }

    public CustomerDTO findById(Long id){
        return modelMapper.mapper().map(dao.findById(id), CustomerDTO.class);
    }

    public CustomerDTO update(Long id, CustomerDTO customer){
        //TODO Verify if nested objects come with Entity type. If comes, change to DTO type and ADD the cascade to list
        //TODO ADD VALIDATION IN UPDATE
        if(validation.validateRequest(customer)) return modelMapper.mapper().map(dao.update(id, customer), CustomerDTO.class);
        throw new InvalidRequestException("Customer validation failed");
    }

}
