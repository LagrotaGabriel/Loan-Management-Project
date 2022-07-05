package br.com.loanapi.dao;

import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.CityEntity;

import java.util.List;

/** Interface used to hold the access to the database repository methods
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 02/07/2022 */
public interface CityDAO {

    CityEntity create(CityDTO city);
    List<CityEntity> findAll();
    CityEntity findById(Long id);
    CityEntity update(Long id, CityDTO city);
    Boolean deleteById(Long id);

}
