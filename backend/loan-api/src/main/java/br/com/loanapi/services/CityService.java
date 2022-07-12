package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.repositories.CityRepository;
import br.com.loanapi.validations.CityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    String CITY_NOT_FOUND = "City not found";

    @Autowired
    ModelMapperConfig modelMapper;

    CityValidation validation = new CityValidation();

    public CityDTO create(CityDTO city){
        if(validation.validateRequest(city))
            return modelMapper.mapper().map(
                    repository.save(modelMapper.mapper().map(city, CityEntity.class)),CityDTO.class);
        throw new InvalidRequestException("City validation failed");
    }

    public List<CityDTO> findAll(){
        if(!repository.findAll().isEmpty())
            return repository.findAll().stream().map(
                    x -> modelMapper.mapper().map(x, CityDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no cities saved in the database");
    }

    public CityDTO findById(Long id){
        Optional<CityEntity> city = repository.findById(id);
        return modelMapper.mapper().map(
                city.orElseThrow(() -> new ObjectNotFoundException(CITY_NOT_FOUND)), CityDTO.class);
    }

    public CityDTO update(Long id, CityDTO city){

        if (validation.validateRequest(city)) {

            CityDTO dto = modelMapper.mapper().map(repository.findById(id), CityDTO.class);

            dto.setCity(city.getCity());
            dto.setAddresses(city.getAddresses());
            dto.setState(city.getState());

            return modelMapper.mapper().map(repository.save(
                    modelMapper.mapper().map(dto, CityEntity.class)), CityDTO.class);
        }
        else{
            throw new InvalidRequestException("City validation failed");
        }

    }

    public Boolean deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ObjectNotFoundException("City not found");
        }
    }

}
