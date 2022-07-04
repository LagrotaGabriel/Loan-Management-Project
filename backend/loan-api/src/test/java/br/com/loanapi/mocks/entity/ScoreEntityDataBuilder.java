package br.com.loanapi.mocks.entity;

import br.com.loanapi.models.entities.ScoreEntity;

import java.text.ParseException;

public class ScoreEntityDataBuilder {

    ScoreEntity scoreEntity;
    ScoreEntityDataBuilder(){}

    public static ScoreEntityDataBuilder builder() throws ParseException {

        ScoreEntityDataBuilder builder = new ScoreEntityDataBuilder();
        builder.scoreEntity = new ScoreEntity();

        builder.scoreEntity.setId(1L);
        builder.scoreEntity.setPontuation(50.0);
        builder.scoreEntity.setCustomer(CustomerEntityDataBuilder.builder().build());

        return builder;
    }

    public ScoreEntity build(){
        return scoreEntity;
    }

}
