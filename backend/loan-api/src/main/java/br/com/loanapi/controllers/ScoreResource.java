package br.com.loanapi.controllers;

import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.ScoreDTO;
import br.com.loanapi.services.ScoreService;
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
@RequestMapping("api/score")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "The api may provide a crud to Score persistence")
public class ScoreResource {

    @Autowired
    ScoreService service;

    @ApiOperation(
            value = "Create",
            notes = "This request will save a Score in database of the project",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Score created with success", response = ScoreDTO.class),
            @ApiResponse(code = 400, message = "Fail on Score creation", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
    })
    @PostMapping
    public ResponseEntity<ScoreDTO> create(@RequestBody ScoreDTO score) {
        return ResponseEntity.ok().body(service.create(score));
    }

    @ApiOperation(
            value = "Find all",
            notes = "This request will find all Phones saved in database",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Scores finded with success", response = ScoreDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Scores saved in database", response = ObjectNotFoundException.class)
    })
    @GetMapping
    public ResponseEntity<List<ScoreDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiOperation(
            value = "Find by id",
            notes = "This request will find a Score saved in database by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Score finded with success", response = ScoreDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Score saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ScoreDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(
            value = "Update",
            notes = "This request will update a Score in database of the project by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Score updated with success", response = ScoreDTO.class),
            @ApiResponse(code = 400, message = "Fail on Score update", response = InvalidRequestException.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Scores saved in database with the passed id",
                    response = ObjectNotFoundException.class),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ScoreDTO> update(@RequestBody ScoreDTO score, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.update(id, score));
    }

    @ApiOperation(
            value = "Delete",
            notes = "This request will delete a Score by id",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Score deleted with success", response = Boolean.class),
            @ApiResponse(code = 401, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "There is no Score saved in database by the passed id",
                    response = ObjectNotFoundException.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}