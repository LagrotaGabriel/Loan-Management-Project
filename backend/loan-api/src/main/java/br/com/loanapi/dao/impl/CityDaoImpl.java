package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.CityDAO;
import br.com.loanapi.models.entities.CityEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityDaoImpl implements CityDAO {

    @Override
    public CityEntity create(CityEntity city) {
        return null;
    }

    @Override
    public List<CityEntity> findAll() {
        return null;
    }

    @Override
    public CityEntity findById(Long id) {
        return null;
    }

    @Override
    public CityEntity update(Long id, CityEntity city) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

}
