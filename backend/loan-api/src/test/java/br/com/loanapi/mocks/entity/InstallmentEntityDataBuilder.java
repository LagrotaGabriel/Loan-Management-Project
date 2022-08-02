package br.com.loanapi.mocks.entity;

import br.com.loanapi.models.entities.InstallmentEntity;

public class InstallmentEntityDataBuilder {

    InstallmentEntityDataBuilder(){}
    InstallmentEntity installment;

    public static InstallmentEntityDataBuilder builder() {

        InstallmentEntityDataBuilder builder = new InstallmentEntityDataBuilder();
        builder.installment = new InstallmentEntity();

        builder.installment.setId(1L);
        builder.installment.setMaturityDate("11-11-2011");
        builder.installment.setPaymentDate("11-11-2021");
        builder.installment.setMonth(4);
        builder.installment.setAmortization(1000.0);
        builder.installment.setInterest(10.0);
        builder.installment.setValue(1100.0);
        builder.installment.setLoan(LoanEntityDataBuilder.builder().build());

        return builder;
    }

    public InstallmentEntity build(){
        return installment;
    }
}
