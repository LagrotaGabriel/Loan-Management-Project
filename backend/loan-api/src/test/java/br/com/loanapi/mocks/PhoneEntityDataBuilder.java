package br.com.loanapi.mocks;

import br.com.loanapi.models.entities.PhoneEntity;
import br.com.loanapi.models.enums.PhoneTypeEnum;

import java.text.ParseException;

public class PhoneEntityDataBuilder {

    PhoneEntityDataBuilder(){}
    PhoneEntity phoneEntity;

    public static PhoneEntityDataBuilder builder() throws ParseException {

        PhoneEntityDataBuilder builder = new PhoneEntityDataBuilder();
        builder.phoneEntity = new PhoneEntity();

        builder.phoneEntity.setId(1L);
        builder.phoneEntity.setPrefix(11);
        builder.phoneEntity.setNumber("97981-5415");
        builder.phoneEntity.setPhoneType(PhoneTypeEnum.MOBILE);
        builder.phoneEntity.setCustomer(CustomerEntityDataBuilder.builder().build());

        return builder;
    }

    public PhoneEntity build(){
        return phoneEntity;
    }

}
