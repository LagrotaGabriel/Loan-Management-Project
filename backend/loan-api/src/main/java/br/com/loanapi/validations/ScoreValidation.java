package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.ScoreDTO;

import static br.com.loanapi.utils.RegexPatterns.SCORE_PATTERN;

public class ScoreValidation {

    public boolean validateRequest(ScoreDTO score) {
        notNull(score);
        verifyPontuation(score.getPontuation());
        return true;
    }

    public boolean notNull(ScoreDTO score) {
        if(score.getPontuation() != null) return true;
        throw new InvalidRequestException("Score validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyPontuation(Double pontuation) {
        if(pontuation.toString().matches(SCORE_PATTERN) && pontuation <= 100.0) return true;
        throw new InvalidRequestException("Pontuation validation failed. The pontuation must be between 0-100");
    }
}
