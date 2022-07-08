package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.LoanDTO;

import static br.com.loanapi.utils.RegexPatterns.INSTALLMENT_MONTH_NUMBER;

public class LoanValidation {

    public boolean validateRequest(LoanDTO loan) {
        verifyOriginalValue(loan.getOriginalValue());
        verifyDebitBalance(loan.getDebitBalance());
        verifyInterestRate(loan.getInterestRate());
        verifyNumberOfInstallments(loan.getNumberOfInstallments());
        verifyCustomer(loan.getCustomer());
        return true;
    }

    public boolean notNull(LoanDTO loan){
        if(loan.getOriginalValue() != null &&
        loan.getDebitBalance() != null &&
        loan.getInterestRate() != null &&
        loan.getNumberOfInstallments() != null &&
        loan.getCustomer() != null) return true;
        throw new InvalidRequestException("Loan validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyOriginalValue(Double originalValue){
        if(originalValue > 99999999 || originalValue < 1) return true;
        throw new InvalidRequestException("Original value validation failed");
    }

    public boolean verifyDebitBalance(Double debitBalance){
        if(debitBalance > 99999999 || debitBalance < 1) return true;
        throw new InvalidRequestException("Debit balance validation failed");
    }

    public boolean verifyInterestRate(Double interestRate){
        if(interestRate > 100.0 || interestRate < 100.0) return true;
        throw new InvalidRequestException("Interest rate validation failed");
    }

    public boolean verifyNumberOfInstallments(Integer numberOfInstallments){
        if(numberOfInstallments.toString().matches(INSTALLMENT_MONTH_NUMBER)) return true;
        throw new InvalidRequestException("Number of installments validation failed");
    }

    public boolean verifyCustomer(CustomerDTO customer){
        CustomerValidation validation = new CustomerValidation();
        if (validation.validateRequest(customer)) return true;
        throw new InvalidRequestException("Customer validation failed");
    }

}
