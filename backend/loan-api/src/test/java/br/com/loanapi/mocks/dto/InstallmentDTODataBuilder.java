package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.utils.DateFormatter;

import java.text.ParseException;

public class InstallmentDTODataBuilder {

    InstallmentDTODataBuilder(){}
    InstallmentDTO installment;

    public static InstallmentDTODataBuilder builder() throws ParseException {

        InstallmentDTODataBuilder builder = new InstallmentDTODataBuilder();
        builder.installment = new InstallmentDTO();

        builder.installment.setId(1L);
        builder.installment.setMaturityDate(DateFormatter.convertStringToDateWithBrPattern("11-11-2011"));
        builder.installment.setPaymentDate(DateFormatter.convertStringToDateWithBrPattern("11-11-2021"));
        builder.installment.setExpired(false);
        builder.installment.setMonth(4);
        builder.installment.setAmortization(1000.0);
        builder.installment.setInterest(10.0);
        builder.installment.setValue(1100.0);
        builder.installment.setLoan(LoanDTODataBuilder.builder().build());

        return builder;
    }

    public InstallmentDTODataBuilder withTooLongMonth(){
        installment.setMonth(1000);
        return this;
    }

    public InstallmentDTODataBuilder withTooLongAmortization(){
        installment.setAmortization(9999999999.0);
        return this;
    }
    public InstallmentDTODataBuilder withTooLongInterest(){
        installment.setInterest(9999999999.0);
        return this;
    }
    public InstallmentDTODataBuilder withTooLongValue(){
        installment.setValue(9999999999.0);
        return this;
    }

    public InstallmentDTO build(){
        return installment;
    }
}
