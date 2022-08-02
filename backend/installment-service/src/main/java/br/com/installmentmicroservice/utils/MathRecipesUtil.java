package br.com.installmentmicroservice.utils;

import br.com.installmentmicroservice.models.dto.LoanDTO;

public class MathRecipesUtil {

    MathRecipesUtil() {}

    public static Double priceInstallmentValueRecipe(LoanDTO loan) {

        double interest = loan.getInterestRate()/100;
        double pow = Math.pow((1 + interest), loan.getNumberOfInstallments());

        Double firstRecipeLine = (pow) * interest;
        Double secondRecipeLine = (pow) - 1;
        Double linesDivided = firstRecipeLine/secondRecipeLine;

        return (linesDivided * loan.getOriginalValue());

    }

}
