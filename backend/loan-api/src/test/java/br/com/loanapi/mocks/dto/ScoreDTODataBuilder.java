package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.ScoreDTO;

import java.text.ParseException;

public class ScoreDTODataBuilder {

    ScoreDTO scoreDTO;
    ScoreDTODataBuilder(){}

    public static ScoreDTODataBuilder builder() throws ParseException {

        ScoreDTODataBuilder builder = new ScoreDTODataBuilder();
        builder.scoreDTO = new ScoreDTO();

        builder.scoreDTO.setId(1L);
        builder.scoreDTO.setPontuation(50.0);
        builder.scoreDTO.setCustomer(CustomerDTODataBuilder.builder().build());

        return builder;
    }

    public ScoreDTODataBuilder withWrongPontuation() {
        scoreDTO.setPontuation(101.0);
        return this;
    }

    public ScoreDTO build(){
        return scoreDTO;
    }

}
