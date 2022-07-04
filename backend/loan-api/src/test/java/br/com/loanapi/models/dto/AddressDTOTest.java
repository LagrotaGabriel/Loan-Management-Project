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
                "postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null), " +
                "customers=null)", AddressDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor(){
        CityDTO city = new CityDTO(1L, "São Paulo", StateEnum.SAO_PAULO, null);

        AddressDTO address = new AddressDTO(1L, "Rua 9", "Lauzane Paulista", 583,
                "02442-090", city, null);

        Assertions.assertEquals("AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583," +
                " postalCode=02442-090, city=CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null), " +
                "customers=null)", address.toString());
    }

    @Test
    @DisplayName("Should test equals and hashcode")
    void shouldTestEqualsAndHashcode(){
        AddressDTO addressDTO = new AddressDTO();
        Assertions.assertEquals(64204658, addressDTO.hashCode());
    }

}
