package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.entities.InstallmentEntity;
import br.com.loanapi.repositories.InstallmentRepository;
import br.com.loanapi.validations.InstallmentValidation;
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

    InstallmentValidation validation = new InstallmentValidation();

    public InstallmentDTO create(InstallmentDTO installment){
        if (validation.validateRequest(installment))
            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(installment, InstallmentEntity.class)), InstallmentDTO.class);
        throw new InvalidRequestException("Installment validation failed");
    }

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

    public InstallmentDTO update(Long id, InstallmentDTO installment) {

        if (validation.validateRequest(installment)) {

            InstallmentDTO dto = modelMapper.mapper().map(findById(id), InstallmentDTO.class);
            dto.setMonth(installment.getMonth());
            dto.setLoan(installment.getLoan());
            dto.setValue(installment.getValue());
            dto.setInterest(installment.getInterest());
            dto.setAmortization(installment.getAmortization());
            dto.setPaymentDate(installment.getPaymentDate());
            dto.setMaturityDate(installment.getMaturityDate());

            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(dto, InstallmentEntity.class)), InstallmentDTO.class);
        }
        else{
            throw new InvalidRequestException("Installment validation failed");
        }

    }

    public Boolean delete(Long id){
        InstallmentEntity entity = modelMapper.mapper().map(findById(id), InstallmentEntity.class);
        repository.delete(entity);
        return true;
    }


}
