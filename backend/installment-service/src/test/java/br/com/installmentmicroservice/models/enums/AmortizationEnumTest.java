package br.com.installmentmicroservice.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: Amortization")
class AmortizationEnumTest {

    @Test
    @DisplayName("Should test getters")
    void shouldTestGetters(){
        String amortization = AmortizationEnum.SAC.getCode() + " " + AmortizationEnum.SAC.getDesc();
        Assertions.assertEquals("1 Sistema de amortização constante", amortization);
    }
}
