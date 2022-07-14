package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.models.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("Select c From CityEntity c where c.city = ?1 and c.state = ?2")
    Optional<CityEntity> findByCityAndState(String city, StateEnum state);

}
