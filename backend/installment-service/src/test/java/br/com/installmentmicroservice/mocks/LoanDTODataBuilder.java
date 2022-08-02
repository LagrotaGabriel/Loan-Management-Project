package br.com.installmentmicroservice.mocks;

import br.com.installmentmicroservice.models.dto.LoanDTO;
import br.com.installmentmicroservice.models.enums.AmortizationEnum;
import br.com.installmentmicroservice.models.enums.PaymentDateEnum;

public class LoanDTODataBuilder {

    LoanDTODataBuilder(){}
    LoanDTO loan;

    public static LoanDTODataBuilder builder() {

        LoanDTODataBuilder builder = new LoanDTODataBuilder();
        builder.loan = new LoanDTO();

        builder.loan.setStartDate("11-11-2011");
        builder.loan.setOriginalValue(5000.0);
        builder.loan.setInterestRate(10.0);
        builder.loan.setNumberOfInstallments(10);
        builder.loan.setPaymentDate(PaymentDateEnum.FIFTH_BUSINESS_DAY);
        builder.loan.setAmortization(AmortizationEnum.SAC);

        return builder;
    }

    public LoanDTODataBuilder withPriceAmortization() {
        this.loan.setAmortization(AmortizationEnum.PRICE);
        return this;
    }

    public LoanDTO build(){
        return loan;
    }

}
