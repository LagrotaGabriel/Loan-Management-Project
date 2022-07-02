package br.com.loanapi.dao;

import br.com.loanapi.models.entities.PhoneEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface PhoneDAO {

    PhoneEntity create(PhoneEntity phone);
    List<PhoneEntity> findAll();
    PhoneEntity findById(Long id);
    PhoneEntity update(Long id,PhoneEntity phone);
    Boolean deleteById(Long id);

}
