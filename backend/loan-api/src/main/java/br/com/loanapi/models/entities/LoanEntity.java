package br.com.loanapi.models.entities;

import br.com.loanapi.models.enums.AmortizationEnum;
import br.com.loanapi.models.enums.PaymentDateEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/** Class that contains all attributes of the object of type LoanEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/LoanEntity.java */
@Entity
@Table(name = "TB_LOAN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_loan", name = "loan")
public class LoanEntity {

    @Id
    @Column(name = "loan_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loan")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "loan_startDate", nullable = false)
    private Date startDate;

    @Column(name = "loan_originalValue", nullable = false)
    private Double originalValue;

    @Column(name = "loan_debitBalance", nullable = false)
    private Double debitBalance;

    @Column(name = "loan_interestRate", nullable = false)
    private Double interestRate;

    @Column(name = "loan_numberOfInstallments", nullable = false)
    private Integer numberOfInstallments;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_paymentDate", nullable = false)
    private PaymentDateEnum paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_amortization", nullable = false)
    private AmortizationEnum amortization;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InstallmentEntity> installments;

}
