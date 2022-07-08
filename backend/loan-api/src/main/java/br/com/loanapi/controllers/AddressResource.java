package br.com.loanapi.controllers;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.services.AddressService;
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
@RequestMapping("api/address")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "The api may provide a crud to Address persistence")
public class AddressResource {

    @Autowired AddressService service;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a address in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Address created with success", response = AddressDTO.class),
            @ApiResponse(code = 400, message = "Fail on address creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody AddressDTO address){
        return ResponseEntity.ok().body(service.create(address));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all addresses saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Addresses finded with success", response = AddressDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no addresses saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a address saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Address finded with success", response = AddressDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no address saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a address in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Address updated with success", response = AddressDTO.class),
            @ApiResponse(code = 400, message = "Fail on address update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no address saved in database with the passed id",
                    response = ObjectNotFoundException.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO address, @PathVariable Long id){
        return ResponseEntity.ok().body(service.update(id, address));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a address by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Address deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no address saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(service.deleteById(id));
    }

}
