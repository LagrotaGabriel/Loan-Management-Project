package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.validations.AddressValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    String ADDRESS_NOT_FOUND = "Address not found";

    @Autowired
    ModelMapperConfig modelMapper;

    AddressValidation validation = new AddressValidation();

    public AddressDTO create(AddressDTO address){
        if(validation.validateRequest(address))
            return modelMapper.mapper().map(
                    repository.save(modelMapper.mapper().map(address, AddressEntity.class)), AddressDTO.class);
        throw new InvalidRequestException("Address validation failed");
    }

    public List<AddressDTO> findAll(){
        if(!repository.findAll().isEmpty()) return repository.findAll().stream()
                .map(x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no addresses saved in the database");
    }

    public AddressDTO findById(Long id){
        Optional<AddressEntity> address = repository.findById(id);
        return modelMapper.mapper().map(address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){
        //TODO Verify if nested objects come with Entity type. If comes, change to DTO type and ADD the cascade to list

        if(validation.validateRequest(address)) {

            Optional<AddressEntity> optionalAddress = repository.findById(id);

            if (optionalAddress.isPresent()) {

                optionalAddress.ifPresent(entity -> entity.setId(id));
                optionalAddress.ifPresent(entity -> entity.setStreet(address.getStreet()));
                optionalAddress.ifPresent(entity -> entity.setNumber(address.getNumber()));
                optionalAddress.ifPresent(entity -> entity.setNeighborhood(address.getNeighborhood()));
                optionalAddress.ifPresent(entity -> entity.setPostalCode(address.getPostalCode()));
                optionalAddress.ifPresent(entity -> entity.setCity(modelMapper.mapper().map(address.getCity(), CityEntity.class)));
                optionalAddress.ifPresent(entity -> entity.setCustomers(address.getCustomers().stream().map(x ->
                        modelMapper.mapper().map(x, CustomerEntity.class)).collect(Collectors.toList())));

                repository.save(optionalAddress.get());
            }

            return modelMapper.mapper().map(
                    optionalAddress.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
        }
        else{
            throw new InvalidRequestException("Address validation failed");
        }

    }

    public Boolean deleteById(Long id) {
        Optional<AddressEntity> address = repository.findById(id);
        if(address.isPresent()){
            repository.deleteById(id);
            return true;
        }
        throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);
    }

}
