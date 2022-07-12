package br.com.loanapi.mocks;

import br.com.loanapi.exceptions.StandartError;

import java.time.LocalDateTime;

public class StandartErrorDataBuilder {

    StandartError standartError;
    StandartErrorDataBuilder(){}

    public static StandartErrorDataBuilder builder() {

        StandartErrorDataBuilder builder = new StandartErrorDataBuilder();
        builder.standartError = new StandartError();

        builder.standartError.setError("Forbiden");
        builder.standartError.setStatus(403);
        builder.standartError.setLocalDateTime(LocalDateTime.parse("2022-06-04T02:06:57.260990900"));
        builder.standartError.setPath("/index");

        return builder;
    }

    public StandartError build() {
        return standartError;
    }
}
