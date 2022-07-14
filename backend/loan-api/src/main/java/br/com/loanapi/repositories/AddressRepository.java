package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("Select a From AddressEntity a where a.street = ?1 and a.number = ?2 and a.postalCode = ?3")
    Optional<AddressEntity> findByStreetNumberAndPostalCode(String street, Integer number, String postalCode);

}
