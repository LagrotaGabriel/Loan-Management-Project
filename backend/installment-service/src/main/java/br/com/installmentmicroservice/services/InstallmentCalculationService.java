package br.com.installmentmicroservice.services;

import br.com.installmentmicroservice.models.dto.InstallmentDTO;
import br.com.installmentmicroservice.models.dto.LoanDTO;
import br.com.installmentmicroservice.models.enums.AmortizationEnum;
import br.com.installmentmicroservice.utils.MathRecipesUtil;
import br.com.installmentmicroservice.utils.MaturityDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InstallmentCalculationService {

    MaturityDateUtil maturityDateUtil = new MaturityDateUtil();
    DecimalFormat df = new DecimalFormat("0.00");

    public List<InstallmentDTO> installmentDistributorByAmortizationType(LoanDTO loan) throws ParseException {

        if (loan.getAmortization().equals(AmortizationEnum.SAC)) {
            return sacCalculation(loan);
        }
        else {
            return priceCalculation(loan);
        }

    }

    public List<InstallmentDTO> sacCalculation(LoanDTO loan) throws ParseException {

        List<InstallmentDTO> installmentsList = new ArrayList<>();

        Double amortizationValue = (loan.getOriginalValue()/ loan.getNumberOfInstallments());
        Double debitBalance = loan.getOriginalValue();

        for (int i = 1; i <= loan.getNumberOfInstallments(); i++) {

            log.info("======================================================================");
            log.warn("[PROGRESS] Starting the creation of the {}° Installment...", i);
            InstallmentDTO installment = new InstallmentDTO();

            installment.setMonth(i);
            installment.setPaymentDate(null);
            installment.setMaturityDate(maturityDateUtil.maturityDateCalculation(loan, i));
            installment.setCreatedDate(LocalDateTime.now());

            installment.setAmortization(Double.valueOf(df.format(amortizationValue).replace(",", ".")));
            log.info("[PROGRESS] Setting the amortization value: ${}...", installment.getAmortization());

            Double interest = (debitBalance/100) * loan.getInterestRate();
            installment.setInterest(Double.valueOf(df.format(interest).replace(",", ".")));
            log.info("[PROGRESS] Calculating and setting the installment interest value: ${}...", installment.getInterest());

            Double value = amortizationValue + interest;
            installment.setValue(Double.valueOf(df.format(value).replace(",", ".")));
            log.info("[PROGRESS] Calculating and setting the installment value: ${}...", installment.getValue());

            debitBalance -= amortizationValue;
            log.info("[PROGRESS] Updating the debitBalance: ${}...", debitBalance);

            installmentsList.add(installment);

        }

        return installmentsList;

    }

    public List<InstallmentDTO> priceCalculation(LoanDTO loan) throws ParseException {

        List<InstallmentDTO> installmentsList = new ArrayList<>();

        Double debitBalance = loan.getOriginalValue();
        Double value = MathRecipesUtil.priceInstallmentValueRecipe(loan);

        for (int i = 1; i <= loan.getNumberOfInstallments(); i++) {

            log.info("======================================================================");
            log.warn("[PROGRESS] Starting the creation of the {}° Installment...", i);
            InstallmentDTO installment = new InstallmentDTO();

            installment.setMonth(i);
            installment.setValue(Double.valueOf(df.format(value).replace(",", ".")));
            installment.setPaymentDate(null);
            installment.setMaturityDate(maturityDateUtil.maturityDateCalculation(loan, i));
            installment.setCreatedDate(LocalDateTime.now());

            Double interest = debitBalance * (loan.getInterestRate()/100);
            installment.setInterest(Double.valueOf(df.format(interest).replace(",", ".")));
            log.info("[PROGRESS] Updating the interest: ${}...", interest);

            Double amortizationValue = (value - interest);
            installment.setAmortization(Double.valueOf(df.format(amortizationValue).replace("," , ".")));
            log.info("[PROGRESS] Updating the amortization value: ${}...", amortizationValue);

            debitBalance -= amortizationValue;
            log.info("[PROGRESS] Updating the debitBalance: ${}...", debitBalance);

            installmentsList.add(installment);

        }

        return installmentsList;

    }

}
