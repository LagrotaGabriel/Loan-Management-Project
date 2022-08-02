package br.com.installmentmicroservice.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Enum: PaymentDate")
class PaymentDateEnumTest {

    @Test
    @DisplayName("Should test getters")
    void shouldTestGetters(){
        String paymentDate = PaymentDateEnum.DAY_TEN.getCode() + " " + PaymentDateEnum.DAY_TEN.getDesc() + " " + PaymentDateEnum.DAY_TEN.getDay();
        Assertions.assertEquals("3 Day ten 10", paymentDate);
    }

}
