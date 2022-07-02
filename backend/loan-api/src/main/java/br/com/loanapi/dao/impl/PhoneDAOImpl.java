package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.PhoneDAO;
import br.com.loanapi.models.entities.PhoneEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDAOImpl implements PhoneDAO {

    @Override
    public PhoneEntity create(PhoneEntity phone) {
        return null;
    }

    @Override
    public List<PhoneEntity> findAll() {
        return null;
    }

    @Override
    public PhoneEntity findById(Long id) {
        return null;
    }

    @Override
    public PhoneEntity update(Long id, PhoneEntity phone) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

}
