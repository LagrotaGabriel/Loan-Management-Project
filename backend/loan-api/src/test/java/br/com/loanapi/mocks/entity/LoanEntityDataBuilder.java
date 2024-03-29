package br.com.loanapi.mocks.entity;

import br.com.loanapi.models.entities.LoanEntity;
import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;

public class LoanEntityDataBuilder {

    LoanEntityDataBuilder(){}
    LoanEntity loan;

    public static LoanEntityDataBuilder builder() {

        LoanEntityDataBuilder builder = new LoanEntityDataBuilder();
        builder.loan = new LoanEntity();

        builder.loan.setId(1L);
        builder.loan.setStartDate("11-11-2011");
        builder.loan.setOriginalValue(5000.0);
        builder.loan.setDebitBalance(2800.0);
        builder.loan.setInterestRate(10.0);
        builder.loan.setNumberOfInstallments(10);
        builder.loan.setPaymentDate(PaymentDateEnum.LAST_BUSINESS_DAY);
        builder.loan.setAmortization(AmortizationEnum.SAC);
        builder.loan.setCustomer(CustomerEntityDataBuilder.builder().build());
        builder.loan.setInstallments(null);

        return builder;
    }

    public LoanEntity build(){
        return loan;
    }

}
