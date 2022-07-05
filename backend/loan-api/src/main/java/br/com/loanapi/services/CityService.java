package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.CityDAO;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.validations.CityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    CityDAO dao;

    @Autowired
    ModelMapperConfig modelMapper;

    CityValidation validation = new CityValidation();

    public CityDTO create(CityDTO city){
        if(validation.validateRequest(city)) return modelMapper.mapper().map(dao.create(city), CityDTO.class);
        throw new InvalidRequestException("City validation failed");
    }

    public List<CityDTO> findAll(){
        if(!dao.findAll().isEmpty()) return dao.findAll().stream().map(x -> modelMapper.mapper().map(x, CityDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no cities saved in the database");
    }

    public CityDTO findById(Long id){
        return modelMapper.mapper().map(dao.findById(id), CityDTO.class);
    }

    public CityDTO update(Long id, CityDTO city){
        //TODO Verify if nested objects come with Entity type. If comes, change to DTO type and ADD the cascade to list
        //TODO ADD VALIDATION IN UPDATE
        return modelMapper.mapper().map(dao.update(id, city), CityDTO.class);
    }

}
