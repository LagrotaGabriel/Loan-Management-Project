package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.repositories.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
@DisplayName("Validation: Address")
@ExtendWith(MockitoExtension.class)
class AddressValidationTest {

    @InjectMocks
    AddressValidation addressValidation;

    @Mock
    AddressRepository repository;

    @Test
    @DisplayName("Should test postal code validation with success")
    void shouldTestPostalCodeValidationWithSuccess(){
        Assertions.assertTrue
                (addressValidation.verifyPostalCode(AddressDTODataBuilder.builder().build().getPostalCode()));
    }

    @Test
    @DisplayName("Should test postal code validation with exception")
    void shouldTestPostalCodeValidationWithException(){
        AddressDTO address = AddressDTODataBuilder.builder().withAlphanumPostalCode().build();
        try{
            addressValidation.verifyPostalCode(address.getPostalCode());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The postal code should follow the pattern" +
                    " xxxxx-xxx, with only numbers", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test number validation with success")
    void shouldTestNumberValidationWithSuccess(){
        Assertions.assertTrue
                (addressValidation.verifyNumber(AddressDTODataBuilder.builder().build().getNumber()));
    }

    @Test
    @DisplayName("Should test number validation with exception")
    void shouldTestNumberValidationWithException(){
        AddressDTO address = AddressDTODataBuilder.builder().withTooLongNumber().build();
        try{
            addressValidation.verifyNumber(address.getNumber());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The number must have only numbers with the max " +
                    "size of 5 characters.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test neighborhood validation with success")
    void shouldTestNeighborhoodValidationWithSuccess(){
        Assertions.assertTrue
                (addressValidation.verifyNeighborhood(AddressDTODataBuilder.builder().build().getNeighborhood()));
    }

    @Test
    @DisplayName("Should test neighborhood validation with exception")
    void shouldTestNeighborhoodValidationWithException(){
        AddressDTO address = AddressDTODataBuilder.builder().withTooLongNeighborhood().build();
        try{
            addressValidation.verifyNeighborhood(address.getNeighborhood());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The neighborhood name is too long " +
                    "(+65 characters).", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test street validation with success")
    void shouldTestStreetValidationWithSuccess(){
        Assertions.assertTrue
                (addressValidation.verifyStreet(AddressDTODataBuilder.builder().build().getStreet()));
    }

    @Test
    @DisplayName("Should test street validation with exception")
    void shouldTestStreetValidationWithException(){
        AddressDTO address = AddressDTODataBuilder.builder().withTooLongStreet().build();
        try{
            addressValidation.verifyStreet(address.getStreet());
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The street name is too long " +
                    "(+65 characters).", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test not null validation with success")
    void shouldTestNotNullValidationWithSuccess(){
        Assertions.assertTrue(addressValidation.notNull(AddressDTODataBuilder.builder().build()));
    }

    @Test
    @DisplayName("Should test not null validation with exception")
    void shouldTestNotNullValidationWithException(){
        AddressDTO address = new AddressDTO();
        try{
            addressValidation.notNull(address);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. Some of the attributes is null " +
                    "or empty.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Should test validate request with success")
    void shouldTestValidateRequestWithSuccess(){
        Assertions.assertTrue(addressValidation.validateRequest(AddressDTODataBuilder.builder().build(), repository));
    }

    @Test
    @DisplayName("Should test validate request with exception")
    void shouldTestValidateRequestWithException(){
        AddressDTO address = AddressDTODataBuilder.builder().withAlphanumPostalCode().build();
        try{
            addressValidation.validateRequest(address, repository);
            Assertions.fail();
        }
        catch(InvalidRequestException exception){
            Assertions.assertEquals("Address validation failed. The postal code should follow the pattern" +
                    " xxxxx-xxx, with only numbers", exception.getMessage());
        }

    }


}
