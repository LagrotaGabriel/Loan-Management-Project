package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.LoanDAO;
import br.com.loanapi.models.entities.LoanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDAOImpl implements LoanDAO {
    @Override
    public LoanEntity create(LoanEntity loan) {
        return null;
    }

    @Override
    public List<LoanEntity> findAll() {
        return null;
    }

    @Override
    public LoanEntity findById(Long id) {
        return null;
    }

    @Override
    public LoanEntity update(Long id, LoanEntity loan) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}
