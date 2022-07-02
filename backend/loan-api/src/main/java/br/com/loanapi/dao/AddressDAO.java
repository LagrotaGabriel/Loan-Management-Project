package br.com.loanapi.dao;

import br.com.loanapi.models.entities.AddressEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface AddressDAO {

    AddressEntity create(AddressEntity address);
    List<AddressEntity> findAll();
    AddressEntity findById(Long id);
    AddressEntity update(Long id, AddressEntity address);
    Boolean deleteById(Long id);

}
