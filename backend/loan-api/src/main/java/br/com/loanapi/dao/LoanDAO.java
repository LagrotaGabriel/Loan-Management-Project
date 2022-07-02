package br.com.loanapi.dao;

import br.com.loanapi.models.entities.LoanEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface LoanDAO {

    LoanEntity create(LoanEntity loan);
    List<LoanEntity> findAll();
    LoanEntity findById(Long id);
    LoanEntity update(Long id, LoanEntity loan);
    Boolean deleteById(Long id);

}
