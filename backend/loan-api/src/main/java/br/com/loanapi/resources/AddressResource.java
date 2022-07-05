package br.com.loanapi.resources;

import br.com.loanapi.dao.AddressDAO;
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

    @Autowired
    AddressService service;

    @Autowired
    AddressDAO dao;

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
            notes = "This request will find all address saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Addresses finded with success", response = AddressDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no address saved in the database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressDTO address, @PathVariable Long id){
        return ResponseEntity.ok().body(service.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok().body(dao.deleteById(id));
    }

}
