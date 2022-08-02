package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.InstallmentDTO;

import static br.com.loanapi.utils.RegexPatterns.INSTALLMENT_MONTH_NUMBER;

public class InstallmentValidation {

    public boolean validateRequest(InstallmentDTO installment) {
        verifyMonth(installment.getMonth());
        verifyAmortization(installment.getAmortization());
        verifyInterest(installment.getInterest());
        verifyValue(installment.getValue());
        return true;
    }

    public boolean notNull(InstallmentDTO installment) {
        if(installment.getMaturityDate() != null &&
        installment.getMonth() != null &&
        installment.getAmortization() != null &&
        installment.getInterest() != null &&
        installment.getValue() != null &&
        installment.getLoan() != null) return true;
        throw new InvalidRequestException("Installment validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyMonth(Integer month){
        if(month.toString().matches(INSTALLMENT_MONTH_NUMBER)) return true;
        throw new InvalidRequestException("Installment validation failed. The month number may be between 1-999");
    }

    public boolean verifyAmortization(Double amortization){
        if (amortization > 0.0 && amortization < 999999999.0) return true;
        throw new InvalidRequestException("Amortization validation failed");
    }

    public boolean verifyInterest(Double interest){
        if (interest > 0.0 && interest < 999999999.0) return true;
        throw new InvalidRequestException("Interest validation failed");
    }

    public boolean verifyValue(Double value){
        if (value > 0.0 && value < 999999999.0) return true;
        throw new InvalidRequestException("Value validation failed");
    }

}
