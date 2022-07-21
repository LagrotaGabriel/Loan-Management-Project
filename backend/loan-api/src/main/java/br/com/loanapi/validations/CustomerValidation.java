package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static br.com.loanapi.utils.RegexPatterns.*;

@Slf4j
public class CustomerValidation {

    public boolean validateRequest(ValidationTypeEnum validationType,
                                   CustomerDTO customer,
                                   CustomerRepository repository,
                                   PhoneRepository phoneRepository){

        log.info("[STARTING] Starting customer validation");

        notNull(customer);
        if (validationType == ValidationTypeEnum.CREATE) {
            exists(customer, repository);
        }
        verifyName(customer.getName());
        verifyLastName(customer.getLastName());
        verifyRg(customer.getRg());
        verifyCpf(customer.getCpf());
        verifyEmail(customer.getEmail());
        verifyBirthDate(customer.getBirthDate());
        verifyPhone(validationType, customer.getPhones(), phoneRepository);

        log.info("[SUCCESS]  Validation successfull");
        return true;
    }

    public boolean notNull(CustomerDTO customer){
        if(customer.getName() != null &&
                customer.getLastName() != null &&
                customer.getEmail() != null &&
                customer.getRg() != null &&
                customer.getCpf() != null) return true;
        throw new InvalidRequestException("Customer validation failed. Some of the attributes is null or empty.");
    }

    public boolean exists(CustomerDTO customerDTO, CustomerRepository repository){

        log.info("[PROGRESS] Validating if the object already exists in database...");

        List<String> errors = new ArrayList<>();

        if (repository.findByRg(customerDTO.getRg()).isPresent()) {
            errors.add("The typed rg already exists in the database");
        }
        if(repository.findByCpf(customerDTO.getCpf()).isPresent()) {
            errors.add("The typed cpf already exists in the database");
        }
        if(repository.findByEmail(customerDTO.getEmail()).isPresent()) {
            errors.add("The typed email already exists in the database");
        }

        if(errors.isEmpty()) {
            return true;
        }
        else if(errors.size() == 1) {
            log.info("[FAILURE] The passed item already exists in database: {}", errors);
            throw new InvalidRequestException("Customer validation failed. Error: " + errors);
        }
        else{
            log.info("[FAILURE] The passed itens already exists in database: {}", errors);
            throw new InvalidRequestException("Customer validation failed. Errors: " + errors);
        }

    }

    public boolean verifyName(String name){
        log.info("[PROGRESS] Validating customer name...");
        if(name.length() <= 65)return true;

        log.error("[FAILURE] Customer name validation failed. The name is too long (+65 characters)");
        throw new InvalidRequestException("Name validation failed. The name is too long (+65 characters)");
    }

    public boolean verifyLastName(String lastName){
        log.info("[PROGRESS] Validating customer last name...");
        if(lastName.length() <= 65) return true;

        log.error("[FAILURE] Customer last name validation failed. The name is too long (+65 characters)");
        throw new InvalidRequestException("Last name validation failed. The last name is too long (+65 characters)");
    }

    public boolean verifyBirthDate(String birthDate){
        log.info("[PROGRESS] Validating customer birth date...");
        if (birthDate.matches(DATE_REGEX)) return true;

        log.error("[FAILURE] Birth date validation failed. The date pattern is incorrect: {}", birthDate);
        throw new InvalidRequestException("Birth date validation failed. The date pattern is incorrect");

    }

    public boolean verifyRg(String rg){
        log.info("[PROGRESS] Validating customer RG...");
        if (rg.matches(RG_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Rg validation failed. The rg pattern is invalid: {}", rg);
        throw new InvalidRequestException("Rg validation failed. The rg pattern is invalid");

    }

    public boolean verifyCpf(String cpf){
        log.info("[PROGRESS] Validating customer CPF...");
        if (cpf.matches(CPF_REGEX_PATTERN)) return true;

        log.error("[FAILURE] CPF validation failed. The CPF pattern is invalid: {}", cpf);
        throw new InvalidRequestException("Cpf validation failed. The cpf pattern is invalid");
    }

    public boolean verifyEmail(String email){
        log.info("[PROGRESS] Validating customer email...");
        if(email.matches(EMAIL_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Email validation failed. The email pattern is invalid: {}", email);
        throw new InvalidRequestException("Email validation failed. The email pattern is invalid");
    }

    public boolean verifyPhone(ValidationTypeEnum validationType, List<PhoneDTO> phones, PhoneRepository phoneRepository){
        log.info("[PROGRESS] Validating customer phones...");
        PhoneValidation validation = new PhoneValidation();
        if (!phones.isEmpty()) {
            for (PhoneDTO phone : phones) {
                if (validation.validateRequest(validationType, phone, phoneRepository)) {
                    log.info("[PROGRESS] Phone {} validated", phone.getNumber());
                }
            }
            return true;
        }
        log.error("[FAILURE] There is no phones sended betwen JSON");
        throw new InvalidRequestException("There is no phones sended betwen JSON");
    }

}
