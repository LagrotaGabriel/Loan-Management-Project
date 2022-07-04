package br.com.loanapi.mocks.entity;

import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.models.enums.StateEnum;

public class CityEntityDataBuilder {

    CityEntity cityEntity;
    CityEntityDataBuilder(){}

    public static CityEntityDataBuilder builder(){

        CityEntityDataBuilder builder = new CityEntityDataBuilder();
        builder.cityEntity = new CityEntity();

        builder.cityEntity.setId(1L);
        builder.cityEntity.setCity("São Paulo");
        builder.cityEntity.setState(StateEnum.SAO_PAULO);
        builder.cityEntity.setAddresses(null);

        return builder;

    }

    public CityEntity build(){
        return cityEntity;
    }

}
