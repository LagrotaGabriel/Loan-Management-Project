package br.com.loanapi.controllers;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.LoanDTO;
import br.com.loanapi.services.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/loan")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "The api may provide a crud to Loan persistence")
public class LoanResource {

    @Autowired
    LoanService service;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a Loan in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Loan created with success", response = LoanDTO.class),
            @ApiResponse(code = 400, message = "Fail on Loan creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<LoanDTO> create(@RequestBody LoanDTO loan) {
        return ResponseEntity.ok().body(service.create(loan));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all loans saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Loans finded with success", response = LoanDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Loans saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a Loan saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Loan finded with success", response = LoanDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Loan saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a Loan in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Loan updated with success", response = LoanDTO.class),
            @ApiResponse(code = 400, message = "Fail on Loan update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Loans saved in database with the passed id",
                    response = ObjectNotFoundException.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> update(@RequestBody LoanDTO loan, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.update(id, loan));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a Loan by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Loan deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Loan saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}