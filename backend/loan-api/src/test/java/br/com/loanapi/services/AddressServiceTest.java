package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.validations.AddressValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("Service: Address")
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    AddressService service;

    @Mock
    AddressValidation addressValidation;

    @Mock
    AddressRepository repository;

    @Mock
    ModelMapperConfig modelMapper;

    //TODO Built AddressService tests again

}
