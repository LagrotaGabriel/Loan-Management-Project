package br.com.loanapi.models.entities;

import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.mocks.entity.CustomerEntityDataBuilder;
import br.com.loanapi.models.enums.StateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Entity: Address")
class AddressEntityTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters(){
        Assertions.assertEquals("AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                        "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, complement=null, customers=null)",
                AddressEntityDataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor(){

        AddressEntity address = new AddressEntity(1L, "Rua 9", "Lauzane Paulista", 583,
                "02442-090", "São Paulo", StateEnum.SAO_PAULO, "Casa 1", null);

        Assertions.assertEquals("AddressEntity(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, " +
                "postalCode=02442-090, city=São Paulo, state=SAO_PAULO, complement=Casa 1, customers=null)",
                address.toString());
    }

    @Test
    @DisplayName("Should test equals and hashcode")
    void shouldTestEqualsAndHashcode(){
        AddressEntity addressEntity = new AddressEntity();
        Assertions.assertEquals(158117644, addressEntity.hashCode());
    }

    @Test
    @DisplayName("Should test addCustomer method")
    void shouldTestAddCustomerMethod() {
        AddressEntity address = AddressEntityDataBuilder.builder().withCustomersList().build();
        address.addCustomer(CustomerEntityDataBuilder.builder().build());
        Assertions.assertNotNull(address.getCustomers());
    }

}
