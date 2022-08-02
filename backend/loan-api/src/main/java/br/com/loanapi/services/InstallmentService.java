package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.entities.InstallmentEntity;
import br.com.loanapi.repositories.InstallmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.LOG_BAR;
import static br.com.loanapi.utils.StringConstants.REQUEST_SUCCESSFULL;

@Slf4j
@Service
public class InstallmentService {

    @Autowired
    InstallmentRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String INSTALLMENT_NOT_FOUND = "Installment not found";

    public List<InstallmentDTO> findAll() {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findAll method...");
        log.info("[PROGRESS] Verifying if there is installments saved in the database...");
        if(!repository.findAll().isEmpty()) {
            log.info(REQUEST_SUCCESSFULL);
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, InstallmentDTO.class))
                    .collect(Collectors.toList());
        }

        log.error("[FAILURE]  There is no installments saved in the database");
        throw new ObjectNotFoundException("There is no installments saved in the database");

    }

    public InstallmentDTO findById(Long id) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findById method...");

        log.info("[PROGRESS] Searching for a loan by id {}...", id);
        Optional<InstallmentEntity> installment = repository.findById(id);

        installment.ifPresent(installmentEntity -> log.info(REQUEST_SUCCESSFULL));
        if(installment.isEmpty()) log.error("[FAILURE]  Installment with id {} not found", id);

        return modelMapper.mapper().map(
                installment.orElseThrow(() -> new ObjectNotFoundException(INSTALLMENT_NOT_FOUND)), InstallmentDTO.class);
    }

}
