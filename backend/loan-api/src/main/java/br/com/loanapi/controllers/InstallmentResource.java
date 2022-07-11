package br.com.loanapi.controllers;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.services.InstallmentService;
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
@RequestMapping("api/installment")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "The api may provide a crud to Installment persistence")
public class InstallmentResource {

    @Autowired
    InstallmentService service;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a Installment in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Installment created with success", response = InstallmentDTO.class),
            @ApiResponse(code = 400, message = "Fail on Installment creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<InstallmentDTO> create(@RequestBody InstallmentDTO installment) {
        return ResponseEntity.ok().body(service.create(installment));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all Installments saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Installments finded with success", response = InstallmentDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Installments saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<InstallmentDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a Installment saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Installment finded with success", response = InstallmentDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Installment saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<InstallmentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a Installment in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Installment updated with success", response = InstallmentDTO.class),
            @ApiResponse(code = 400, message = "Fail on Installment update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Installment saved in database with the passed id",
                    response = ObjectNotFoundException.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<InstallmentDTO> update(@RequestBody InstallmentDTO installment, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.update(id, installment));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a Installment by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Installment deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Installment saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}