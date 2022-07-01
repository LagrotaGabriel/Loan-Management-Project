package br.com.loanapi.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: PhoneType")
class PhoneTypeEnumTest {

    @Test
    @DisplayName("Should test getters")
    void shouldTestGetters(){
        String phoneType = PhoneTypeEnum.MOBILE.getCode() + " " + PhoneTypeEnum.MOBILE.getDesc();
        Assertions.assertEquals("2 Mobile", phoneType);
    }

}
