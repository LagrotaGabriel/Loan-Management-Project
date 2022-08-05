package br.com.installmentmicroservice.mocks;

import br.com.installmentmicroservice.models.dto.InstallmentDTO;

public class InstallmentDTODataBuilder {

    InstallmentDTODataBuilder(){}
    InstallmentDTO installment;

    public static InstallmentDTODataBuilder builder() {

        InstallmentDTODataBuilder builder = new InstallmentDTODataBuilder();
        builder.installment = new InstallmentDTO();

        builder.installment.setId(1L);
        builder.installment.setMaturityDate("11-11-2011");
        builder.installment.setPaymentDate("11-11-2011");
        builder.installment.setMonth(4);
        builder.installment.setAmortization(1000.0);
        builder.installment.setInterest(10.0);
        builder.installment.setValue(1100.0);
        builder.installment.setNotes("[]");
        builder.installment.setLoan(LoanDTODataBuilder.builder().build());

        return builder;
    }

    public InstallmentDTO build(){
        return installment;
    }
}
