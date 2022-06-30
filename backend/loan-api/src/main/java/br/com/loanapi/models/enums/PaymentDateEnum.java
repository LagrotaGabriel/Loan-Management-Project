package br.com.loanapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentDateEnum {

    FIRST_BUSINESS_DAY(1, "First business day"),
    FIFTH_BUSINESS_DAY(2, "Fifth business day"),
    DAY_TEN(3, "Day ten"),
    DAY_TWENTY(4, "Day twenty"),
    LAST_BUSINESS_DAY(5, "Last business day");

    private final Integer code;
    private final String desc;

}
