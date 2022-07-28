package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.enums.PhoneTypeEnum;

public class PhoneDTODataBuilder {

    PhoneDTODataBuilder(){}
    PhoneDTO phoneDTO;

    public static PhoneDTODataBuilder builder() {

        PhoneDTODataBuilder builder = new PhoneDTODataBuilder();
        builder.phoneDTO = new PhoneDTO();

        builder.phoneDTO.setId(1L);
        builder.phoneDTO.setPrefix(11);
        builder.phoneDTO.setNumber("97981-5415");
        builder.phoneDTO.setPhoneType(PhoneTypeEnum.MOBILE);
        builder.phoneDTO.setCustomerJsonId(1L);
        builder.phoneDTO.setCustomer(CustomerDTODataBuilder.builder().build());

        return builder;
    }

    public PhoneDTODataBuilder withMockedCustomer(){
        phoneDTO.setCustomer(CustomerDTODataBuilder.builder().withPhoneList().withAddresssWithCustomers().build());
        return this;
    }

    public PhoneDTODataBuilder withWrongPrefix(){
        phoneDTO.setPrefix(111);
        return this;
    }

    public PhoneDTODataBuilder withWrongNumber(){
        phoneDTO.setNumber("(11)97981-5415");
        return this;
    }

    public PhoneDTO build(){
        return phoneDTO;
    }

}
