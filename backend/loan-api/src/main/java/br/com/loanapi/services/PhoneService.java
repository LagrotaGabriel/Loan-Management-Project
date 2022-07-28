package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.models.entities.PhoneEntity;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CustomerRepository;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.PhoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.loanapi.utils.StringConstants.*;

@Slf4j
@Service
public class PhoneService {

    @Autowired
    PhoneRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    PhoneValidation validation = new PhoneValidation();

    public PhoneDTO create(PhoneDTO phone){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        log.info("[PROGRESS] Finding a customer in database betwen the id {}...", phone.getCustomerId());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(phone.getCustomerId());

        if (optionalCustomer.isEmpty()) {
            log.warn(CUSTOMER_NOT_FOUND_LOG);
            throw new InvalidRequestException(CUSTOMER_NOT_FOUND);
        }

        if (validation.validateRequest(ValidationTypeEnum.CREATE, phone, repository)) {

            log.info("[PROGRESS] Getting the address of the customer and assigning this into a variable...");
            CustomerEntity customer = optionalCustomer.get();
            AddressEntity address = customer.getAddress();

            log.info("[PROGRESS] Adding the phone into customer phone list...");
            customer.addPhone(modelMapper.mapper().map(phone, PhoneEntity.class));

            log.info("[PROGRESS] Updating the address customer list into AddressEntity class...");
            address.updateCustomer(customer);

            log.info("[PROGRESS] Saving at database in cascade: AddressEntity (Update) -> CustomerEntity (Insert) -> PhoneEntity (New)...");
            addressRepository.save(address);

            log.info(REQUEST_SUCCESSFULL);
            return phone;

        }

        throw new InvalidRequestException("Phone validation failed");

    }

    public List<PhoneDTO> findAll() {
        if(!repository.findAll().isEmpty())
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, PhoneDTO.class))
                    .collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no phones saved in the database");
    }

    public PhoneDTO findById(Long id) {
        Optional<PhoneEntity> phone = repository.findById(id);
        return modelMapper.mapper().map(
                phone.orElseThrow(() -> new ObjectNotFoundException(PHONE_NOT_FOUND)), PhoneDTO.class);
    }

    public PhoneDTO update(Long id, PhoneDTO phone) {

        if (validation.validateRequest(ValidationTypeEnum.UPDATE, phone, repository)) {

            PhoneDTO dto = modelMapper.mapper().map(findById(id), PhoneDTO.class);
            dto.setNumber(phone.getNumber());
            dto.setPrefix(phone.getPrefix());
            dto.setPhoneType(phone.getPhoneType());
            dto.setCustomer(phone.getCustomer());

            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(dto, PhoneEntity.class)), PhoneDTO.class);
        }
        else{
            throw new InvalidRequestException("Phone validation failed");
        }

    }

    public Boolean delete(Long id){
        PhoneEntity entity = modelMapper.mapper().map(findById(id), PhoneEntity.class);
        repository.delete(entity);
        return true;
    }
}
