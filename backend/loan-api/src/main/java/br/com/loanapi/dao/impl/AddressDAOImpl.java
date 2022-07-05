package br.com.loanapi.dao.impl;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.AddressDAO;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    AddressRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String ADDRESS_NOT_FOUND = "Address not found";

    @Override
    public AddressEntity create(AddressDTO address) {
        return repository.save(modelMapper.mapper().map(address, AddressEntity.class));
    }

    @Override
    public List<AddressEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public AddressEntity findById(Long id) {
        Optional<AddressEntity> address = repository.findById(id);
        return address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND));
    }

    @Override
    public AddressEntity update(Long id, AddressDTO address) {

        Optional<AddressEntity> optionalAddress = repository.findById(id);

        if(optionalAddress.isPresent()){

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

        return optionalAddress.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND));

    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<AddressEntity> address = repository.findById(id);
        if(address.isPresent()){
            repository.deleteById(id);
            return true;
        }
        throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);
    }

}
