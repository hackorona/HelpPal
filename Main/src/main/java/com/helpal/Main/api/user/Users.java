package com.helpal.Main.api.user;

import com.helpal.model.User;
import com.helpal.model.UserRepository;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(value="Users Management API")
public class Users {

    private final UserRepository repository;

    public Users(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping
    @ApiOperation(value = "List all available Users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    List<User> getAll(){
        return repository.findAll();
    }

    @PostMapping()
    @ApiOperation(value = "Create a new User")
    ResponseEntity<User> create(
            @ApiParam(value = "User object to create", required = true)
            @Valid
            @RequestBody User user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a new User by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved User"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<User> getOne(
            @ApiParam(value = "Id of the User to be retrieved", required = true)
            @PathVariable Long id)
            throws UserNotFoundException {
        return ResponseEntity.ok(repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PutMapping()
    @ApiOperation(value = "Update User's data by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated User's data"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<User> update(
        @ApiParam(value = "User object to update", required = true)
        @Valid
        @RequestBody User user)
            throws UserNotFoundException {
            final User updatedUser = repository.findById(user.getId())
                    .map(existingUser -> repository.save(user))
                    .orElseThrow(() -> new UserNotFoundException(user.getId()));
            return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a User by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the User"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<String> delete(
        @ApiParam(value = "Id of the User to be deleted", required = true)
        @PathVariable Long id)
            throws UserNotFoundException {
        {
            repository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            repository.deleteById(id);
            return ResponseEntity.ok("deleted");
        }
    }
}
