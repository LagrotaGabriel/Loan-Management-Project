package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.utils.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("DTO: Customer")
class CustomerDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() throws ParseException {

        Assertions.assertEquals("CustomerDTO(id=1, name=João, lastName=da Silva, " +
                "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, rg=55.626.926-4, " +
                "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreDTO(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null)", CustomerDTODataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() throws ParseException {

        ScoreDTO score = new ScoreDTO(1L, 50.0, null);
        CustomerDTO customer = new CustomerDTO(
                1L,
                "João",
                "da Silva",
                DateFormatter.convertStringToDateWithBrPattern("11-11-2011"),
                DateFormatter.convertStringToDateWithBrPattern("11-11-2021"),
                "55.626.926-4",
                "391.534.277-44",
                "joao@email.com",
                AddressDTODataBuilder.builder().build(),
                score,
                null,
                null);

        Assertions.assertEquals("CustomerDTO(id=1, name=João, lastName=da Silva, " +
                "birthDate=Fri Nov 11 00:00:00 BRST 2011, signUpDate=Thu Nov 11 00:00:00 BRT 2021, rg=55.626.926-4, " +
                "cpf=391.534.277-44, email=joao@email.com, address=AddressDTO(id=1, street=Rua 9, " +
                "neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, " +
                "state=SAO_PAULO, addresses=null), customers=null), score=ScoreDTO(id=1, pontuation=50.0, " +
                "customer=null), phones=null, loans=null)", customer.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcodeMethod(){
        CustomerDTO customer = new CustomerDTO();
        Assertions.assertEquals(-195339799, customer.hashCode());
    }

}
