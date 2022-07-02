package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.AddressDAO;
import br.com.loanapi.models.entities.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDAOImpl implements AddressDAO {

    @Override
    public AddressEntity create(AddressEntity address) {
        return null;
    }

    @Override
    public List<AddressEntity> findAll() {
        return null;
    }

    @Override
    public AddressEntity findById(Long id) {
        return null;
    }

    @Override
    public AddressEntity update(Long id, AddressEntity address) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

}
