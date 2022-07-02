package br.com.loanapi.dao;

import br.com.loanapi.models.entities.InstallmentEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface InstallmentDAO {

    InstallmentEntity create(InstallmentEntity installment);
    List<InstallmentEntity> findAll();
    InstallmentEntity findByid(Long id);
    InstallmentEntity update(Long id, InstallmentEntity installment);
    Boolean deleteById(Long id);

}
