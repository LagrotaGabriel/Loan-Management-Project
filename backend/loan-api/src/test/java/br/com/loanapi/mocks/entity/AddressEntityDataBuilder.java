package br.com.loanapi.mocks.entity;

import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CustomerEntity;
import br.com.loanapi.models.enums.StateEnum;

import java.util.ArrayList;
import java.util.List;

public class AddressEntityDataBuilder {

    AddressEntity address;
    AddressEntityDataBuilder(){}

    public static AddressEntityDataBuilder builder(){

        AddressEntityDataBuilder builder = new AddressEntityDataBuilder();
        builder.address = new AddressEntity();

        builder.address.setId(1L);
        builder.address.setStreet("Rua 9");
        builder.address.setNeighborhood("Lauzane Paulista");
        builder.address.setNumber(583);
        builder.address.setPostalCode("02442-090");
        builder.address.setCity("São Paulo");
        builder.address.setState(StateEnum.SAO_PAULO);
        builder.address.setCustomers(null);

        return builder;

    }

    public AddressEntityDataBuilder withCustomersList() {
        List<CustomerEntity> customerEntityList = new ArrayList<>();
        customerEntityList.add(CustomerEntityDataBuilder.builder().build());
        address.setCustomers(customerEntityList);
        return this;
    }

    public AddressEntity build(){
        return address;
    }
}
