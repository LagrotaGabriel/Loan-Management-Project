package br.com.loanapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationTypeEnum {

    UPDATE(1, "Update"),
    CREATE(2, "Create");

    private final int code;
    private final String desc;

}
