package br.com.loanapi.models.dto;

import br.com.loanapi.mocks.dto.AddressDTODataBuilder;
import br.com.loanapi.mocks.dto.CustomerDTODataBuilder;
import br.com.loanapi.mocks.dto.LoanDTODataBuilder;
import br.com.loanapi.mocks.dto.PhoneDTODataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DisplayName("DTO: Customer")
class CustomerDTOTest {

    @Test
    @DisplayName("Should test getters and setters")
    void shouldTestGettersAndSetters() {

        Assertions.assertEquals("CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                        "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, " +
                        "pontuation=0.0, address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, " +
                        "number=583, postalCode=02442-090, city=São Paulo, state=SAO_PAULO, complement=null, " +
                        "customers=[]), phones=[], loans=[])",
                CustomerDTODataBuilder.builder().build().toString());

    }

    @Test
    @DisplayName("Should test all args constructor")
    void shouldTestAllArgsConstructor() {

        CustomerDTO customer = new CustomerDTO(
                1L,
                "João",
                "da Silva",
                "11-11-2011",
                "11-11-2021",
                "55.626.926-4",
                "391.534.277-44",
                "joao@email.com",
                100.0,
                AddressDTODataBuilder.builder().build(),
                new ArrayList<>(),
                new ArrayList<>());

        Assertions.assertEquals("CustomerDTO(id=1, name=João, lastName=da Silva, birthDate=11-11-2011, " +
                "signUpDate=11-11-2021, rg=55.626.926-4, cpf=391.534.277-44, email=joao@email.com, pontuation=100.0, " +
                "address=AddressDTO(id=1, street=Rua 9, neighborhood=Lauzane Paulista, number=583, postalCode=02442-090, " +
                "city=São Paulo, state=SAO_PAULO, complement=null, customers=[]), phones=[], loans=[])", customer.toString());

    }

    @Test
    @DisplayName("Should test hashcode")
    void shouldTestHashcodeMethod(){
        CustomerDTO customer = new CustomerDTO();
        Assertions.assertNotEquals(-1794027312, customer.hashCode());
    }

    @Test
    @DisplayName("Should test addPhone method")
    void shouldTestSetPhoneListMethod() {
        CustomerDTO customer = CustomerDTODataBuilder.builder().withPhoneList().build();

        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTODataBuilder.builder().build());

        customer.setPhoneList(phones);
        Assertions.assertNotNull(customer.getPhones());
    }

    @Test
    @DisplayName("Should test addLoan method")
    void shouldTestAddLoanMethod() {
        CustomerDTO customer = CustomerDTODataBuilder.builder().withLoanList().build();
        customer.addLoan(LoanDTODataBuilder.builder().build());
        Assertions.assertNotNull(customer.getLoans());
    }

}
