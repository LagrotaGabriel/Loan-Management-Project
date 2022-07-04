package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.CityEntityDataBuilder;
import br.com.loanapi.models.enums.StateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: City")
class CityEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters(){
        Assertions.assertEquals("CityEntity(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                CityEntityDataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructorMethod(){
        CityEntity city = new CityEntity(1L, "São Paulo", StateEnum.SAO_PAULO, null);
        Assertions.assertEquals("CityEntity(id=1, city=São Paulo, state=SAO_PAULO, addresses=null)",
                city.toString());
    }

    @Test
    @DisplayName("Should test equals and hashcode")
    void shouldTestEqualsAndHashCodeMethod(){
        CityEntity cityEntity = new CityEntity();
        Assertions.assertEquals(21100921, cityEntity.hashCode());
    }

}
