package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.ConnectionFailedException;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.models.entities.LoanEntity;
import br.com.loanapi.proxys.InstallmentServiceProxy;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.LoanRepository;
import br.com.loanapi.validations.LoanValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.*;

@Slf4j
@Service
public class LoanService {

    @Autowired
    LoanRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InstallmentServiceProxy proxy;

    @Autowired
    ModelMapperConfig modelMapper;

    String LOAN_NOT_FOUND = "Loan not found";

    LoanValidation validation = new LoanValidation();

    public LoanDTO create(Long customerId, LoanDTO loan){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        log.info("[PROGRESS] Searching for a customer with the customerId received in JSON...");
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);

        if (validation.validateRequest(loan) && optionalCustomer.isPresent()) {

            CustomerEntity customer = optionalCustomer.get();
            log.warn("[INFO] Customer found: {} {}", customer.getName(), customer.getLastName());

            log.info("[PROGRESS] Trying to connect into installment calculation micro service...");
            ResponseEntity<List<InstallmentDTO>> installmentMicroServiceRequest = proxy.calculateInstallments(loan);

            if (installmentMicroServiceRequest.getStatusCode() == HttpStatus.OK) {
                log.warn("[INFO] Microservice connection successfull");
                List<InstallmentDTO> loanInstallments = installmentMicroServiceRequest.getBody();

                log.info("[PROGRESS] Setting the calculated installments into the loan...");
                loan.setInstallments(loanInstallments);

                log.info("[PROGRESS] Setting the debit balance equals than the original loan value");
                loan.setDebitBalance(loan.getOriginalValue());

                log.info("[PROGRESS] Saving the loan into the customer with id {}", customerId);
                customer.addLoan(modelMapper.mapper().map(loan, LoanEntity.class));

                log.info("[PROGRESS] Updating the customer in the database...");
                customerRepository.save(customer);
            }
            else {
                log.error(MICROSERVICE_CONNECTION_FAILED_LOG);
                throw new ConnectionFailedException("Installment Microservice connection failed");
            }

            return loan;
        }
        else {
            log.warn(CUSTOMER_NOT_FOUND_LOG);
            throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
        }

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
