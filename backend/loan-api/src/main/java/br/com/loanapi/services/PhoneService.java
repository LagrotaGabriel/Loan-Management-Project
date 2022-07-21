package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.entities.PhoneEntity;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.PhoneRepository;
import br.com.loanapi.validations.PhoneValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String PHONE_NOT_FOUND = "Phone not found";

    PhoneValidation validation = new PhoneValidation();

    public PhoneDTO create(PhoneDTO phone){
        if (validation.validateRequest(ValidationTypeEnum.CREATE, phone, repository))
            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(phone, PhoneEntity.class)), PhoneDTO.class);
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
