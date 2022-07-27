package br.com.loanapi.validations;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.models.dto.PhoneDTO;
import br.com.loanapi.models.enums.ValidationTypeEnum;
import br.com.loanapi.repositories.PhoneRepository;
import lombok.extern.slf4j.Slf4j;

import static br.com.loanapi.utils.RegexPatterns.PHONE_PREFIX_REGEX_PATTERN;
import static br.com.loanapi.utils.RegexPatterns.PHONE_REGEX_PATTERN;

@Slf4j
public class PhoneValidation {

    public boolean validateRequest(ValidationTypeEnum validationType,
                                   PhoneDTO phone,
                                   PhoneRepository repository) {

        log.info("[STARTING] Starting Phone validation");

        notNull(phone);
        if (validationType == ValidationTypeEnum.CREATE) {
            exists(phone, repository);
        }
        verifyPrefix(phone.getPrefix());
        verifyNumber(phone.getNumber());

        log.info("[SUCCESS]  Validation successfull");
        return true;
    }

    public boolean exists(PhoneDTO phoneDTO, PhoneRepository repository) {
        log.info("[PROGRESS] Validating if the object already exists in database...");
        if (repository.findByPrefixAndNumber(phoneDTO.getPrefix(), phoneDTO.getNumber()).isEmpty()) return true;

        log.error("[FAILURE] Phone validation failed. The phone already exists in database");
        throw new InvalidRequestException("The phone already exist at database");
    }

    public boolean notNull(PhoneDTO phone) {
        log.info("[PROGRESS] Validating if the object have null attributes...");
        if (phone.getPrefix() != null &&
                phone.getNumber() != null &&
                phone.getPhoneType() != null) return true;

        log.error("[FAILURE] Phone validation failed. Some of the attributes are null or empty");
        throw new InvalidRequestException("Phone validation failed. Some of the attributes is null or empty");
    }

    public boolean verifyPrefix(Integer prefix) {
        log.info("[PROGRESS] Validating phone prefix...");
        if (prefix.toString().matches(PHONE_PREFIX_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Phone prefix validation failed: {}", prefix);
        throw new InvalidRequestException("Prefix validation failed. The pattern must be two numbers. Example: 99");
    }

    public boolean verifyNumber(String number) {
        log.info("[PROGRESS] Validating phone number...");
        if (number.matches(PHONE_REGEX_PATTERN)) return true;

        log.error("[FAILURE] Phone number validation failed: {}", number);
        throw new InvalidRequestException(
                "Phone number validation failed. Pattern example: xxxxx-xxxx (for mobile) or xxxx-xxxx (for landline)");
    }

}
