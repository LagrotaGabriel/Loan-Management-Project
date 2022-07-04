package br.com.loanapi.mocks.dto;

import br.com.loanapi.models.dto.AddressDTO;

public class AddressDTODataBuilder {

    AddressDTO address;
    AddressDTODataBuilder(){}

    public static AddressDTODataBuilder builder(){

        AddressDTODataBuilder builder = new AddressDTODataBuilder();
        builder.address = new AddressDTO();

        builder.address.setId(1L);
        builder.address.setStreet("Rua 9");
        builder.address.setNeighborhood("Lauzane Paulista");
        builder.address.setNumber(583);
        builder.address.setPostalCode("02442-090");
        builder.address.setCity(CityDTODataBuilder.builder().build());
        builder.address.setCustomers(null);

        return builder;

    }

    public AddressDTO build(){
        return address;
    }
}
