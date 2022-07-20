package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    @Query("Select p From PhoneEntity p Where p.prefix=?1 and p.number=?2")
    Optional<PhoneEntity> findByPrefixAndNumber(Integer prefix, String number);

}
