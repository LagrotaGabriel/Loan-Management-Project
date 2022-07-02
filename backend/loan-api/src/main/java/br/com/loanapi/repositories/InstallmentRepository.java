package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {}
