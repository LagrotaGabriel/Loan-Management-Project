package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;

/** Class that contains all attributes of the object of type InstallmentEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github hhttps://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/InstallmentEntity.java */
@Entity
@Table(name = "TB_INSTALLMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InstallmentEntity {

    @Id
    @Column(name = "installment_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installment_maturityDate", nullable = false)
    private String maturityDate;

    @Column(name = "installment_paymentDate")
    private String paymentDate;

    @Column(name = "installment_expired", nullable = false)
    private Boolean expired;

    @Column(name = "installment_month", nullable = false)
    private Integer month;

    @Column(name = "installment_amortization", nullable = false)
    private Double amortization;

    @Column(name = "installment_interest", nullable = false)
    private Double interest;

    @Column(name = "installment_value", nullable = false)
    private Double value;

    @ManyToOne(targetEntity = LoanEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

}
