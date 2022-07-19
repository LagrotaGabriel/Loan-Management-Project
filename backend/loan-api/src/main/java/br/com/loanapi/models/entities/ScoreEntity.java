package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;

/** Class that contains all attributes of the object of type ScoreEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/ScoreEntity.java */
@Entity
@Table(name = "TB_SCORE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ScoreEntity {

    @Id
    @Column(name = "score_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private Double pontuation;

    @OneToOne(mappedBy = "score")
    private CustomerEntity customer;
}
