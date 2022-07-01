package br.com.loanapi.mocks;

import br.com.loanapi.models.entities.AddressEntity;

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
        builder.address.setCity(CityEntityDataBuilder.builder().build());
        builder.address.setCustomers(null);

        return builder;

    }

    public AddressEntity build(){
        return address;
    }
}
