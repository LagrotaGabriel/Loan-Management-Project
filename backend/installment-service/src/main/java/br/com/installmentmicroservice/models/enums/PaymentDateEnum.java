package br.com.installmentmicroservice.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentDateEnum {

    FIRST_BUSINESS_DAY(1, "First business day", 1),
    FIFTH_BUSINESS_DAY(2, "Fifth business day", 5),
    DAY_TEN(3, "Day ten", 10),
    DAY_TWENTY(4, "Day twenty", 20),
    LAST_BUSINESS_DAY(5, "Last business day", 30);

    private final Integer code;
    private final String desc;
    private final Integer day;

}
