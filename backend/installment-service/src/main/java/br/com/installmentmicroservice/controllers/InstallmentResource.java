package br.com.installmentmicroservice.controllers;

import br.com.installmentmicroservice.models.dto.InstallmentDTO;
import br.com.installmentmicroservice.models.dto.LoanDTO;
import br.com.installmentmicroservice.services.InstallmentCalculationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/installment-service")
@Api(value = "This api may provide the endpoints to calculate the installments of a Loan of the Loan-Project")
public class InstallmentResource {

    @Autowired
    InstallmentCalculationService service;

    @ApiOperation(
            value = "Installment calculator",
            notes = "This endpoint will calculate the installments of a Loan"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Installments returned with success", response = InstallmentDTO.class),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 500, message = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<List<InstallmentDTO>> calculateInstallments(@RequestBody LoanDTO loanDTO) throws ParseException {
        return ResponseEntity.ok().body(service.installmentDistributorByAmortizationType(loanDTO));
    }

}
