package br.com.loanapi.controllers;

import br.com.loanapi.dao.CityDAO;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/city")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "The api may provide a crud to City persistence")
public class CityResource {

    @Autowired CityService service;
    @Autowired CityDAO dao;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a city in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "City created with success", response = CityDTO.class),
            @ApiResponse(code = 400, message = "Fail on city creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO city){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(city));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all cities saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cities finded with success", response = CityDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no cities saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a city saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "City finded with success", response = CityDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no city saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a city in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "City updated with success", response = CityDTO.class),
            @ApiResponse(code = 400, message = "Fail on city update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no city saved in database with the passed id",
                response = ObjectNotFoundException.class),
    })
    @PutMapping("{id}")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO city, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, city));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a city by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "City deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no city saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(dao.deleteById(id));
    }
}
