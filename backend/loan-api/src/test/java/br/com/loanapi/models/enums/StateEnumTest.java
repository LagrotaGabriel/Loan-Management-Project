package br.com.loanapi.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: State")
class StateEnumTest {

    @Test
    @DisplayName("Should test getters")
    void shouldTestGetters(){
        String state = StateEnum.ACRE.getCode() + " " + StateEnum.ACRE.getDesc() + " " + StateEnum.ACRE.getPrefix();
        Assertions.assertEquals("1 Acre AC", state);
    }

}
