package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.PhoneDTO;

import static br.com.loanapi.utils.RegexPatterns.PHONE_PREFIX_REGEX_PATTERN;
import static br.com.loanapi.utils.RegexPatterns.PHONE_REGEX_PATTERN;

public class PhoneValidation {

    public boolean validateRequest(PhoneDTO phone) {
        notNull(phone);
        verifyPrefix(phone.getPrefix());
        verifyNumber(phone.getNumber());
        return true;
    }

    public boolean notNull(PhoneDTO phone) {
        if(phone.getPrefix() != null &&
            phone.getNumber() != null &&
            phone.getPhoneType() != null) return true;
        throw new InvalidRequestException("Phone validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyPrefix(Integer prefix) {
        if(prefix.toString().matches(PHONE_PREFIX_REGEX_PATTERN)) return true;
        throw new InvalidRequestException("Prefix validation failed. The pattern must be two numbers. Example: 99");
    }

    public boolean verifyNumber(String number) {
        if(number.matches(PHONE_REGEX_PATTERN)) return true;
        throw new InvalidRequestException(
                "Phone number validation failed. Pattern example: xxxxx-xxxx (for mobile) or xxxx-xxxx (for landline)");
    }

}
