package br.com.loanapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception used in cases of invalid request params
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 04/07/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/tree/master/backend/loan-api/resources/exceptions/InvalidRequestException.java */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message) {
        super(message);
    }

}
