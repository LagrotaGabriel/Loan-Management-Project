package br.com.loanapi.dao;

import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.entities.CustomerEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface CustomerDAO {

    CustomerEntity create(CustomerDTO customer);
    List<CustomerEntity> findAll();
    CustomerEntity findById(Long id);
    CustomerEntity update(Long id, CustomerDTO customer);
    Boolean deleteById(Long id);

}
