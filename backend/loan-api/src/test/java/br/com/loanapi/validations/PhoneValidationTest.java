package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.mocks.entity.PhoneEntityDataBuilder;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@DisplayName("Validation: Phone")
@ExtendWith(MockitoExtension.class)
class PhoneValidationTest {

    @InjectMocks
    PhoneValidation validation;

    @Mock
    PhoneRepository repository;

    @Test
    @DisplayName("Should validate prefix validation with success")
    void shouldValidatePrefixValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyPrefix(PhoneDTODataBuilder.builder().build().getPrefix()));
    }

    @Test
    @DisplayName("Should validate prefix validation with exception")
    void shouldValidatePrefixValidationWithException(){

        try{
            validation.verifyPrefix(PhoneDTODataBuilder.builder().withWrongPrefix().build().getPrefix());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Prefix validation failed. The pattern must be two numbers. Example: 99",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate phone number validation with success")
    void shouldValidatePhoneNumberValidationWithSuccess() {
        Assertions.assertTrue(validation.verifyNumber(PhoneDTODataBuilder.builder().build().getNumber()));
    }

    @Test
    @DisplayName("Should validate phone number validation with exception")
    void shouldValidatePhoneNumberValidationWithException(){

        try{
            validation.verifyNumber(PhoneDTODataBuilder.builder().withWrongNumber().build().getNumber());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Phone number validation failed. Pattern example: xxxxx-xxxx (for mobile) or xxxx-xxxx (for landline)",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate not null validation with success")
    void shouldValidateNotNullValidationWithSuccess() {
        Assertions.assertTrue(validation.notNull(PhoneDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should validate not null validation with exception")
    void shouldValidateNotNullValidationWithException() {
        PhoneDTO phone = PhoneDTODataBuilder.builder().build();
        phone.setNumber(null);

        try{
            validation.notNull(phone);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Phone validation failed. Some of the attributes is null or empty",
                    exception.getMessage());
        }

    }

    @Test
    @DisplayName("Should validate validate request with success")
    void shouldValidateValidateRequestWithSuccess() {
        Assertions.assertTrue(validation.validateRequest(ValidationTypeEnum.CREATE, PhoneDTODataBuilder.builder().build(), repository));
    }

    @Test
    @DisplayName("Should test exists validation with success")
    void shouldTestExistsValidationWithSuccess() {
        Mockito.when(repository.findByPrefixAndNumber(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertTrue(validation.exists(ValidationTypeEnum.CREATE, PhoneDTODataBuilder.builder().build(), repository));
    }

    @Test
    @DisplayName("Should test exists validation with exception")
    void shouldTestExistsValidationWithException() {
        Mockito.when(repository.findByPrefixAndNumber(Mockito.any(), Mockito.any())).thenReturn(Optional.of(PhoneEntityDataBuilder.builder().build()));
        try {
            validation.exists(ValidationTypeEnum.CREATE, PhoneDTODataBuilder.builder().build(), repository);
            Assertions.fail();
        }
        catch (InvalidRequestException exception) {
            Assertions.assertEquals("The JSON Phone already exist at database",
                    exception.getMessage());
        }
    }

}
