package br.com.loanapi.utils;

public class RegexPatterns {

    RegexPatterns(){}

    public static final String SCORE_PATTERN = "[0-9]{1,3}.[0-9]{1,2}";

    public static final String INSTALLMENT_MONTH_NUMBER = "[0-9]{1,3}";

    public static final String STREET_NUMBER_REGEX_PATTERN = "[0-9]{1,5}";

    public static final String POSTAL_CODE_REGEX_PATTERN = "[0-9]{5}-[0-9]{3}";

    public static final String RG_REGEX_PATTERN = "[0-9]{2}.[0-9]{3}.[0-9]{3}-[0-9]";

    public static final String CPF_REGEX_PATTERN = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}";

    public static final String CUSTOMER_NOT_FOUND = "Customer not found";

    public static final String EMAIL_REGEX_PATTERN =
            "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    public static final String PHONE_REGEX_PATTERN = "[1-9][0-9]{3,4}-[0-9]{4}";

    public static final String PHONE_PREFIX_REGEX_PATTERN = "[1-9]{2}";

    public static final String DATE_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
}
