package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.models.entities.LoanEntity;
import br.com.loanapi.repositories.LoanRepository;
import br.com.loanapi.validations.LoanValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    LoanRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String LOAN_NOT_FOUND = "Loan not found";

    LoanValidation validation = new LoanValidation();

    public LoanDTO create(LoanDTO loan){
        if (validation.validateRequest(loan))
            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(loan, LoanEntity.class)), LoanDTO.class);
        throw new InvalidRequestException("Loan validation failed");
    }

    public List<LoanDTO> findAll() {
        if(!repository.findAll().isEmpty())
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, LoanDTO.class))
                    .collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no loans saved in the database");
    }

    public LoanDTO findById(Long id) {
        Optional<LoanEntity> loan = repository.findById(id);
        return modelMapper.mapper().map(
                loan.orElseThrow(() -> new ObjectNotFoundException(LOAN_NOT_FOUND)), LoanDTO.class);
    }

    public LoanDTO update(Long id, LoanDTO loan) {

        if (validation.validateRequest(loan)) {

            LoanDTO dto = modelMapper.mapper().map(findById(id), LoanDTO.class);
            dto.setOriginalValue(loan.getOriginalValue());
            dto.setDebitBalance(loan.getOriginalValue());
            dto.setAmortization(loan.getAmortization());
            dto.setInterestRate(loan.getInterestRate());
            dto.setNumberOfInstallments(loan.getNumberOfInstallments());
            dto.setCustomer(loan.getCustomer());
            dto.setPaymentDate(loan.getPaymentDate());
            dto.setInstallments(loan.getInstallments());
            dto.setStartDate(loan.getStartDate());

            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(dto, LoanEntity.class)), LoanDTO.class);
        }
        else{
            throw new InvalidRequestException("Loan validation failed");
        }

    }

    public Boolean delete(Long id){
        LoanEntity entity = modelMapper.mapper().map(findById(id), LoanEntity.class);
        repository.delete(entity);
        return true;
    }

}
