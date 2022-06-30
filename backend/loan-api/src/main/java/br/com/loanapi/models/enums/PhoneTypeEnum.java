package br.com.loanapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneTypeEnum {

    LANDLINE(1, "Landline"),
    MOBILE(2, "Mobile");

    private final Integer code;
    private final String desc;

}
