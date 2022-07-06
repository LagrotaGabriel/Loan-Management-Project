package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.models.dto.ScoreDTO;
import br.com.loanapi.utils.DateFormatter;

import java.text.ParseException;

public class CustomerDTODataBuilder {

    CustomerDTO customer;
    CustomerDTODataBuilder(){}

    public static CustomerDTODataBuilder builder() throws ParseException {

        CustomerDTODataBuilder builder = new CustomerDTODataBuilder();
        builder.customer = new CustomerDTO();

        ScoreDTO score = new ScoreDTO(1L, 50.0, null);

        builder.customer.setId(1L);
        builder.customer.setName("João");
        builder.customer.setLastName("da Silva");
        builder.customer.setBirthDate(DateFormatter.convertStringToDateWithBrPattern("11-11-2011"));
        builder.customer.setSignUpDate(DateFormatter.convertStringToDateWithBrPattern("11-11-2021"));
        builder.customer.setRg("55.626.926-4");
        builder.customer.setCpf("391.534.277-44");
        builder.customer.setEmail("joao@email.com");
        builder.customer.setAddress(AddressDTODataBuilder.builder().build());
        builder.customer.setScore(score);
        builder.customer.setPhones(null);
        builder.customer.setLoans(null);

        return builder;
    }

    public CustomerDTODataBuilder withTooLongName(){
        customer.setName("This is a too long name that will throws a exception at the validation class");
        return this;
    }

    public CustomerDTODataBuilder withTooLongLastName(){
        customer.setLastName("This is a too long last name that will throws a exception at the validation class");
        return this;
    }

    public CustomerDTODataBuilder withRealisticBirthDate() throws ParseException {
        customer.setBirthDate(DateFormatter.convertStringToDateWithBrPattern("11-11-1998"));
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

    public CustomerDTO build(){
        return customer;
    }

}
