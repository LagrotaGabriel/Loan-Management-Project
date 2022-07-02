package br.com.loanapi.repositories;

import br.com.loanapi.models.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {}
