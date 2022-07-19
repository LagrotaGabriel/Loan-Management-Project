package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.models.enums.StateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: Address")
class AddressDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters(){
        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)",
                AddressDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor(){

        AddressDTO address = new AddressDTO(1L, "Rua 9", "Lauzane Paulista", 583,
                "02442-090", "São Paulo", StateEnum.SAO_PAULO, null);

        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, customers=null)", address.toString());
    }

    @Test
    @DisplayName("Should test equals and hashcode")
    void shouldTestEqualsAndHashcode(){
        AddressDTO addressDTO = new AddressDTO();
        Assertions.assertEquals(-506892473, addressDTO.hashCode());
    }

}
