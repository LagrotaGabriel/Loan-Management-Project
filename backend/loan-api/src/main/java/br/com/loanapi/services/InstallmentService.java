package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.entities.InstallmentEntity;
import br.com.loanapi.repositories.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstallmentService {

    @Autowired
    InstallmentRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String INSTALLMENT_NOT_FOUND = "Installment not found";

    public List<InstallmentDTO> findAll() {
        if(!repository.findAll().isEmpty())
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, InstallmentDTO.class))
                    .collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no installments saved in the database");
    }

    public InstallmentDTO findById(Long id) {
        Optional<InstallmentEntity> installment = repository.findById(id);
        return modelMapper.mapper().map(
                installment.orElseThrow(() -> new ObjectNotFoundException(INSTALLMENT_NOT_FOUND)), InstallmentDTO.class);
    }

}
