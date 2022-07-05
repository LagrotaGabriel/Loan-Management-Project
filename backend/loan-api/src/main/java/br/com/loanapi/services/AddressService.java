package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.AddressDAO;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.validations.AddressValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    ModelMapperConfig modelMapper;

    AddressValidation validation = new AddressValidation();

    public AddressDTO create(AddressDTO address){
        if(validation.validateRequest(address)) return modelMapper.mapper().map(addressDAO.create(address), AddressDTO.class);
        throw new InvalidRequestException("Address validation failed");
    }

    public List<AddressDTO> findAll(){
        if(!addressDAO.findAll().isEmpty()) return addressDAO.findAll().stream()
                .map(x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no addresses saved in the database");
    }

    public AddressDTO findById(Long id){
        return modelMapper.mapper().map(addressDAO.findById(id), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){
        //TODO Verify if nested objects come with Entity type. If comes, change to DTO type and ADD the cascade to list
        //TODO ADD VALIDATION IN UPDATE
        return modelMapper.mapper().map(addressDAO.update(id, address), AddressDTO.class);
    }

}
