package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.mocks.entity.PhoneEntityDataBuilder;
import br.com.loanapi.models.enums.PhoneTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
@DisplayName("Entity: Phone")
class PhoneEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("PhoneEntity(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customer=CustomerEntity(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=null, address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, complement=null, " +
                        "customers=null), phones=null, loans=null))",
                PhoneEntityDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        PhoneEntity phone = new PhoneEntity(
                1L,
                11,
                "97981-5415",
                PhoneTypeEnum.MOBILE,
                CustomerEntityDataBuilder.builder().build());

        Assertions.assertEquals("PhoneEntity(id=1, prefix=11, number=97981-5415, phoneType=MOBILE, " +
                        "customer=CustomerEntity(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=null, address=AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, complement=null, " +
                        "customers=null), phones=null, loans=null))",
                phone.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode(){
        PhoneEntity phone = new PhoneEntity();
        Assertions.assertEquals(1244954382, phone.hashCode());
    }


}
