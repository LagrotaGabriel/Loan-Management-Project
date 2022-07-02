package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.InstallmentDAO;
import br.com.loanapi.models.entities.InstallmentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallmentDAOImpl implements InstallmentDAO {

    @Override
    public InstallmentEntity create(InstallmentEntity installment) {
        return null;
    }

    @Override
    public List<InstallmentEntity> findAll() {
        return null;
    }

    @Override
    public InstallmentEntity findByid(Long id) {
        return null;
    }

    @Override
    public InstallmentEntity update(Long id, InstallmentEntity installment) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

}
