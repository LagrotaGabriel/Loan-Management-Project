package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.CityDTODataBuilder;
import br.com.loanapi.mocks.entity.CityEntityDataBuilder;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.repositories.CityRepository;
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

@DisplayName("Validation: City")
@ExtendWith(MockitoExtension.class)
class CityValidationTest {

    @InjectMocks
    CityValidation cityValidation;

    @Mock
    CityRepository repository;

    @Test
    @DisplayName("Should test city validation with success")
    void shouldTestCityValidationWithSuccess(){
        Assertions.assertTrue
                (cityValidation.verifyCity(CityDTODataBuilder.builder().build().getCity()));
    }

    @Test
    @DisplayName("Should test city validation with exception")
    void shouldTestCityValidationWithException(){
        CityDTO city = CityDTODataBuilder.builder().withTooLongCity().build();
        try{
            cityValidation.verifyCity(city.getCity());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. The city name is too long (+65 characters).",
                    exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test exists validation with success")
    void shouldTestExistsValidationWithSuccess(){
        Mockito.when(repository.findByCityAndState(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertTrue(cityValidation.exists(CityDTODataBuilder.builder().build(), repository));
    }

    @Test
    @DisplayName("Should test exists validation with exception")
    void shouldTestExistsValidationWithException(){
        Mockito.when(repository.findByCityAndState(Mockito.any(), Mockito.any())).thenReturn(Optional.of(CityEntityDataBuilder.builder().build()));
        try{
            cityValidation.exists(CityDTODataBuilder.builder().build(), repository);
            Assertions.fail();
        }
        catch (InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. The request body city already exists in database",
                    exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test not null validation with success")
    void shouldTestNotNullValidationWithSuccess(){
        Assertions.assertTrue(cityValidation.notNull(CityDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should test not null validation with exception")
    void shouldTestNotNullValidationWithException(){
        CityDTO city = new CityDTO();
        try{
            cityValidation.notNull(city);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. Some of the attributes is null " +
                    "or empty.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test validate request with success")
    void shouldTestValidateRequestWithSuccess(){
        Mockito.when(repository.findByCityAndState(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertTrue(cityValidation.validateRequest(CityDTODataBuilder.builder().build(), repository));
    }

    @Test
    @DisplayName("Should test validate request with exception")
    void shouldTestValidateRequestWithException(){
        Mockito.when(repository.findByCityAndState(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        CityDTO city = CityDTODataBuilder.builder().withTooLongCity().build();
        try{
            cityValidation.validateRequest(city, repository);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("City validation failed. The city name is too long (+65 characters).",
                    exception.getMessage());
        }

    }
}
