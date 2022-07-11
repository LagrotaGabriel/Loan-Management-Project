package br.com.loanapi.utils;

public class RegexPatterns {

    RegexPatterns(){}

    public static final String INSTALLMENT_MONTH_NUMBER = "[0-9]{1,3}";

    public static final String STREET_NUMBER_REGEX_PATTERN = "[0-9]{1,5}";

    public static final String POSTAL_CODE_REGEX_PATTERN = "[0-9]{5}-[0-9]{3}";

    public static final String RG_REGEX_PATTERN = "[0-9]{2}.[0-9]{3}.[0-9]{3}-[0-9]";

    public static final String CPF_REGEX_PATTERN = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}";

    public static final String EMAIL_REGEX_PATTERN =
            "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";


}
