package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("Select c From CustomerEntity c where c.rg = ?1")
    Optional<CustomerEntity> findByRg(String rg);

    @Query("Select c From CustomerEntity c where c.cpf = ?1")
    Optional<CustomerEntity> findByCpf(String cpf);

    @Query("Select c From CustomerEntity c where c.email = ?1")
    Optional<CustomerEntity> findByEmail(String email);

}
