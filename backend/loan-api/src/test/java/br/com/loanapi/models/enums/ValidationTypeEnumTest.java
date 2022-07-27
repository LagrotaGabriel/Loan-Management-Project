package br.com.loanapi.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: ValidationType")
class ValidationTypeEnumTest {

    @Test
    @DisplayName("Should test getters")
    void shouldTestGetters(){
        String validationType = ValidationTypeEnum.CREATE.getCode() + " " + ValidationTypeEnum.CREATE.getDesc();
        Assertions.assertEquals("2 Create", validationType);
    }

}
