package br.com.loanapi.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Class that contains all attributes of the object of type CustomerEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 30/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/blob/master/backend/loan-api/src/main/java/br/com/loanapi/models/entities/CustomerEntity.java */
@Entity
@Table(name = "TB_CUSTOMER")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 65)
    private String name;
    @Column(name = "last_name", length = 65)
    private String lastName;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "signup_date")
    private String signUpDate;
    @Column(name = "rg", length = 12, unique = true)
    private String rg;
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;
    @Column(name = "email", length = 65, unique = true)
    private String email;
    @Column(name = "pontuation")
    private Double pontuation;

    @ManyToOne(targetEntity = AddressEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private AddressEntity address;

    @OneToMany(targetEntity = PhoneEntity.class, mappedBy = "customer", cascade ={CascadeType.ALL})
    private List<PhoneEntity> phones = new ArrayList<>();

    @OneToMany(targetEntity = LoanEntity.class, mappedBy = "customer", cascade = {CascadeType.ALL})
    private List<LoanEntity> loans = new ArrayList<>();

    public void addPhone(PhoneEntity phone) {
        phone.setCustomer(this);
        this.getPhones().add(phone);
    }

    public void addLoan(LoanEntity loanDTO) {
        loanDTO.setCustomer(this);
        this.loans.add(loanDTO);
    }


}
