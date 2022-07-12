package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.entities.AddressEntity;
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
        return modelMapper.mapper().map(
                address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){

        if (validation.validateRequest(address)) {

            AddressDTO dto = modelMapper.mapper().map(repository.findById(id), AddressDTO.class);

            dto.setStreet(address.getStreet());
            dto.setNumber(address.getNumber());
            dto.setCity(address.getCity());
            dto.setCustomers(address.getCustomers());
            dto.setNeighborhood(address.getNeighborhood());
            dto.setPostalCode(address.getPostalCode());

            return modelMapper.mapper().map(repository.save(
                    modelMapper.mapper().map(dto, AddressEntity.class)), AddressDTO.class);
        }
        else{
            throw new InvalidRequestException("Address validation failed");
        }

    }

    public Boolean deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ObjectNotFoundException("Address not found");
        }
    }

}
