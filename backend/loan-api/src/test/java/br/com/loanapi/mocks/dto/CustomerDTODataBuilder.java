package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.dto.ScoreDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTODataBuilder {

    CustomerDTO customer;
    CustomerDTODataBuilder(){}

    public static CustomerDTODataBuilder builder() {

        CustomerDTODataBuilder builder = new CustomerDTODataBuilder();
        builder.customer = new CustomerDTO();

        ScoreDTO score = new ScoreDTO(1L, 50.0, null);

        builder.customer.setId(1L);
        builder.customer.setName("João");
        builder.customer.setLastName("da Silva");
        builder.customer.setBirthDate("11-11-2011");
        builder.customer.setSignUpDate("11-11-2021");
        builder.customer.setRg("55.626.926-4");
        builder.customer.setCpf("391.534.277-44");
        builder.customer.setEmail("joao@email.com");
        builder.customer.setAddress(AddressDTODataBuilder.builder().build());
        builder.customer.setScore(score);
        builder.customer.setPhones(new ArrayList<>());
        builder.customer.setLoans(new ArrayList<>());

        return builder;
    }

    public CustomerDTODataBuilder withAddresssWithCustomers() {
        customer.setAddress(AddressDTODataBuilder.builder().withCustomersList().build());
        return this;
    }

    public CustomerDTODataBuilder withPhoneList() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTODataBuilder.builder().build());
        customer.setPhones(phones);
        return this;
    }

    public CustomerDTODataBuilder withLoanList() {
        List<LoanDTO> loans = new ArrayList<>();
        loans.add(LoanDTODataBuilder.builder().build());
        customer.setLoans(loans);
        return this;
    }

    public CustomerDTODataBuilder withTooLongName(){
        customer.setName("This is a too long name that will throws a exception at the validation class");
        return this;
    }

    public CustomerDTODataBuilder withTooLongLastName(){
        customer.setLastName("This is a too long last name that will throws a exception at the validation class");
        return this;
    }

    public CustomerDTODataBuilder withRealisticBirthDate() {
        customer.setBirthDate("1998-07-21");
        return this;
    }

    public CustomerDTODataBuilder withInvalidRg(){
        customer.setRg("555.826.822-21");
        return this;
    }

    public CustomerDTODataBuilder withInvalidCpf(){
        customer.setCpf("55.826.822-9");
        return this;
    }

    public CustomerDTODataBuilder withInvalidEmail(){
        customer.setEmail("gabriel.com");
        return this;
    }

    public CustomerDTODataBuilder withPhone(){
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTODataBuilder.builder().build());
        customer.setPhones(phones);
        return this;
    }

    public CustomerDTO build(){
        return customer;
    }

}
