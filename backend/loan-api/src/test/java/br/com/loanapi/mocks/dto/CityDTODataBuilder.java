package br.com.loanapi.mocks.dto;

import br.com.loanapi.mocks.entity.AddressEntityDataBuilder;
import br.com.loanapi.mocks.entity.CityEntityDataBuilder;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.enums.StateEnum;

import java.util.ArrayList;
import java.util.List;

public class CityDTODataBuilder {

    CityDTO cityDTO;
    CityDTODataBuilder(){}

    public static CityDTODataBuilder builder(){

        CityDTODataBuilder builder = new CityDTODataBuilder();
        builder.cityDTO = new CityDTO();

        builder.cityDTO.setId(1L);
        builder.cityDTO.setCity("São Paulo");
        builder.cityDTO.setState(StateEnum.SAO_PAULO);
        builder.cityDTO.setAddresses(null);

        return builder;

    }

    public CityDTODataBuilder withAddress(){
        List<AddressDTO> addresses = new ArrayList<>();
        addresses.add(AddressDTODataBuilder.builder().build());
        cityDTO.setAddresses(addresses);
        return this;
    }

    public CityDTODataBuilder withTooLongCity(){
        cityDTO.setCity("A city name too long that will throws a too long exception in the validation class.");
        return this;
    }

    public CityDTO build(){
        return cityDTO;
    }

}
