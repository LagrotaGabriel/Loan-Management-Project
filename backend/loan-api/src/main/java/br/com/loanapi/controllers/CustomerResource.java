package br.com.loanapi.controllers;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CustomerDTO;
import br.com.loanapi.services.CustomerService;
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
@RequestMapping("api/customer")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "The api may provide a crud to Customer persistence")
public class CustomerResource {

    @Autowired
    CustomerService service;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a customer in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer created with success", response = CustomerDTO.class),
            @ApiResponse(code = 400, message = "Fail on customer creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok().body(service.create(customer));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all customers saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customers finded with success", response = CustomerDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no customers saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a customer saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer finded with success", response = CustomerDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no customer saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a customer in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer updated with success", response = CustomerDTO.class),
            @ApiResponse(code = 400, message = "Fail on customer update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no customer saved in database with the passed id",
                    response = ObjectNotFoundException.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customer, @PathVariable Long id){
        return ResponseEntity.ok().body(service.update(id, customer));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a customer by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no customer saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(service.deleteById(id));
    }

}
