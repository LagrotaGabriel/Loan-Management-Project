package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.CustomerDAO;
import br.com.loanapi.models.entities.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        return null;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return null;
    }

    @Override
    public CustomerEntity findById(Long id) {
        return null;
    }

    @Override
    public CustomerEntity update(Long id, CustomerEntity customer) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}
