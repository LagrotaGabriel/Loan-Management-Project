package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import br.com.loanapi.models.enums.PhoneTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("DTO: Phone")
class PhoneDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                        "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), score=ScoreDTO(id=1, " +
                        "pontuation=50.0, customer=null), phones=null, loans=null))",
                PhoneDTODataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        PhoneDTO phone = new PhoneDTO(
                1L,
                11,
                "97981-5415",
                PhoneTypeEnum.MOBILE,
                CustomerDTODataBuilder.builder().build());

        Assertions.assertEquals("PhoneDTO(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customer=CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                        "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null), " +
                        "score=ScoreDTO(id=1, pontuation=50.0, customer=null), phones=null, loans=null))",
                phone.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        PhoneDTO phone = new PhoneDTO();
        Assertions.assertEquals(1244954382, phone.hashCode());
    }


}
