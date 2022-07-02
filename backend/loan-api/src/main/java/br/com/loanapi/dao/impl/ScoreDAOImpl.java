package br.com.loanapi.dao.impl;

import br.com.loanapi.dao.ScoreDAO;
import br.com.loanapi.models.entities.ScoreEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreDAOImpl implements ScoreDAO {

    @Override
    public ScoreEntity create(ScoreEntity score) {
        return null;
    }

    @Override
    public List<ScoreEntity> findAll() {
        return null;
    }

    @Override
    public ScoreEntity findById(Long id) {
        return null;
    }

    @Override
    public ScoreEntity update(Long id, ScoreEntity score) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}
