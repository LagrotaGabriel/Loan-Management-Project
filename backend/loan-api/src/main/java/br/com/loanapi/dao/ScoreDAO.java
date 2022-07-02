package br.com.loanapi.dao;

import br.com.loanapi.models.entities.ScoreEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface ScoreDAO {

    ScoreEntity create(ScoreEntity score);
    List<ScoreEntity> findAll();
    ScoreEntity findById(Long id);
    ScoreEntity update(Long id, ScoreEntity score);
    Boolean deleteById(Long id);

}
