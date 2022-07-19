package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;

public class LoanDTODataBuilder {

    LoanDTODataBuilder(){}
    LoanDTO loan;

    public static LoanDTODataBuilder builder() {

        LoanDTODataBuilder builder = new LoanDTODataBuilder();
        builder.loan = new LoanDTO();

        builder.loan.setId(1L);
        builder.loan.setStartDate("11-11-2011");
        builder.loan.setOriginalValue(5000.0);
        builder.loan.setDebitBalance(2800.0);
        builder.loan.setInterestRate(10.0);
        builder.loan.setNumberOfInstallments(10);
        builder.loan.setPaymentDate(PaymentDateEnum.FIFTH_BUSINESS_DAY);
        builder.loan.setAmortization(AmortizationEnum.SAC);
        builder.loan.setCustomer(CustomerDTODataBuilder.builder().build());
        builder.loan.setInstallments(null);

        return builder;
    }

    public LoanDTODataBuilder withTooLongOriginalValue(){
        loan.setOriginalValue(9999999999.0);
        return this;
    }

    public LoanDTODataBuilder withTooLongDebitBalance(){
        loan.setDebitBalance(9999999999.0);
        return this;
    }

    public LoanDTODataBuilder withTooLongInterestRate(){
        loan.setInterestRate(101.0);
        return this;
    }

    public LoanDTODataBuilder withTooLongNumberOfInstallments(){
        loan.setNumberOfInstallments(9999);
        return this;
    }

    public LoanDTO build(){
        return loan;
    }

}
