package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.CityDTODataBuilder;
import br.com.loanapi.models.enums.StateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("DTO: City")
class CityDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters(){
        Assertions.assertEquals("CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                CityDTODataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructorMethod(){
        CityDTO city = new CityDTO(1L, "São Paulo", StateEnum.SAO_PAULO, null);
        Assertions.assertEquals("CityDTO(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                city.toString());
    }

    @Test
    @DisplayName("Should test equals and hashcode")
    void shouldTestEqualsAndHashCodeMethod(){
        CityDTO cityDTO = new CityDTO();
        Assertions.assertEquals(21100921, cityDTO.hashCode());
    }

}
