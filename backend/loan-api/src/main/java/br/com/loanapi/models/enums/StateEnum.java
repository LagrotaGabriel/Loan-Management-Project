package br.com.loanapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {

    ACRE(1, "Acre", "AC"),
    ALAGOAS(2, "Alagoas", "AL"),
    AMAPA(3, "Amapá", "AP"),
    AMAZONAS(4, "Amazonas", "AM"),
    BAHIA(5, "Bahia", "BA"),
    CEARA(6, "Ceará", "CE"),
    DISTRITO_FEDERAL(7, "Distrito federal", "DF"),
    ESPIRITO_SANTO(8, "Espírito Santo", "ES"),
    GOIAS(9, "Goiás", "GO"),
    MARANHAO(10, "Maranhão", "MA"),
    MATO_GROSSO(11, "Mato Grosso", "MT"),
    MATO_GROSSO_DO_SUL(12, "Mato Grosso do Sul", "MS"),
    MINAS_GERAIS(13, "Minas Gerais", "MG"),
    PARA(14, "Pará", "PA"),
    PARAIBA(15, "Paraíba", "PB"),
    PARANA(16, "Paraná", "PR"),
    PERNAMBUCO(17, "Pernambuco", "PE"),
    PIAUI(18, "Piauí", "PI"),
    RIO_DE_JANEIRO(19, "Rio de Janeiro", "RJ"),
    RIO_GRANDE_DO_NORTE(20, "Rio Grande do Norte", "RN"),
    RIO_GRANDE_DO_SUL(21, "Rio Grande do Sul", "RS"),
    RONDONIA(22, "Rondônia", "RO"),
    RORAIMA(23, "Roraima", "RR"),
    SANTA_CATARINA(24, "Santa Catarina", "SC"),
    SAO_PAULO(25, "São Paulo", "SP"),
    SERGIPE(26, "Sergipe", "SE"),
    TOCANTINS(27, "Tocantins","TO");

    private final Integer code;
    private final String desc;
    private final String prefix;

}
