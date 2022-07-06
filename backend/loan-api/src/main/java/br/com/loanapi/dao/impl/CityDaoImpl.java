package br.com.loanapi.dao.impl;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.dao.CityDAO;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityDaoImpl implements CityDAO {

    @Autowired
    CityRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String CITY_NOT_FOUND = "City not found";

    @Override
    public CityEntity create(CityDTO city) {
        return repository.save(modelMapper.mapper().map(city, CityEntity.class));
    }

    @Override
    public List<CityEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public CityEntity findById(Long id) {
        Optional<CityEntity> city = repository.findById(id);
        return city.orElseThrow(() -> new ObjectNotFoundException(CITY_NOT_FOUND));
    }

    @Override
    public CityEntity update(Long id, CityDTO city) {
        CityEntity cityById = findById(id);
        cityById.setCity(city.getCity());
        cityById.setState(city.getState());
        cityById.setAddresses(city.getAddresses().stream().map(x -> modelMapper.mapper().map(x, AddressEntity.class))
                .collect(Collectors.toList()));
        return repository.save(cityById);
    }

    @Override
    public Boolean deleteById(Long id) {
        CityEntity city = findById(id);
        repository.delete(city);
        return true;
    }

}
