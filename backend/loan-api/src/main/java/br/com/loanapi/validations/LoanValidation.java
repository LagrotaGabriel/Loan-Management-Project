package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.LoanDTO;

import static br.com.loanapi.utils.RegexPatterns.INSTALLMENT_MONTH_NUMBER;

public class LoanValidation {

    public boolean validateRequest(LoanDTO loan){
        notNull(loan);
        verifyOriginalValue(loan.getOriginalValue());
        verifyInterestRate(loan.getInterestRate());
        verifyNumberOfInstallments(loan.getNumberOfInstallments());
        return true;
    }

    public boolean notNull(LoanDTO loan){ //TODO Improve the not null request validation
        if(loan.getOriginalValue() != null &&
        loan.getInterestRate() != null &&
        loan.getNumberOfInstallments() != null) return true;
        throw new InvalidRequestException("Loan validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyOriginalValue(Double originalValue) {
        if (originalValue > 0.0 && originalValue < 999999999.0) return true;
        throw new InvalidRequestException("Original value validation failed");
    }

    public boolean verifyDebitBalance(Double debitBalance) {
        if (debitBalance > 0.0 && debitBalance < 999999999.0) return true;
        throw new InvalidRequestException("Debit balance validation failed");
    }

    public boolean verifyInterestRate(Double interestRate) {
        if (interestRate > 0.0 && interestRate < 100.0) return true;
        throw new InvalidRequestException("Interest rate validation failed");
    }

    public boolean verifyNumberOfInstallments(Integer numberOfInstallments){
        if (numberOfInstallments.toString().matches(INSTALLMENT_MONTH_NUMBER)) return true;
        throw new InvalidRequestException("Number of installments validation failed");
    }

}
