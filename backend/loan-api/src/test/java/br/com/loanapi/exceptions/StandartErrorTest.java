package br.com.loanapi.exceptions;

import br.com.loanapi.mocks.StandartErrorDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@DisplayName("Exception: standartError")
class StandartErrorTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {
        Assertions.assertEquals("StandartError(localDateTime=2022-06-04T02:06:57.260990900, status=403, " +
                "error=Forbiden, path=/index)", StandartErrorDataBuilder.builder().build().toString());
    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        StandartError error = new StandartError(LocalDateTime.parse("" +
                "2022-06-04T02:06:57.260990900"),
                403,
                "Forbiden",
                "/index");

        Assertions.assertEquals("StandartError(localDateTime=2022-06-04T02:06:57.260990900, status=403, " +
                "error=Forbiden, path=/index)", StandartErrorDataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcode() {
        Assertions.assertEquals(2028541041, StandartErrorDataBuilder.builder().build().hashCode());
    }

}
