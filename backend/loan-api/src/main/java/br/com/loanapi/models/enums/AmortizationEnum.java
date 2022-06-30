package br.com.loanapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmortizationEnum {

    SAC(1, "Sistema de amortização constante"),
    PRICE(2, "Price");

    private final Integer code;
    private final String desc;

}
