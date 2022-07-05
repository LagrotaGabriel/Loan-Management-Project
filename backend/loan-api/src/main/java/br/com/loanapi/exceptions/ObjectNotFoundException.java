package br.com.loanapi.exceptions;

/** Exception used in cases of invalid request params
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 04/07/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/Loan-Project/tree/master/backend/loan-api/resources/exceptions/ObjectNotFoundException.java */
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(message);
    }

}
