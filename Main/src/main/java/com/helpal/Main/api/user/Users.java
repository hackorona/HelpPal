package com.helpal.Main.api.user;

import com.helpal.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "Users Management API")
public class Users {

    private UserService userService;

    public Users(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "List all available Users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping()
    @ApiOperation(value = "Create a new User")
    ResponseEntity<User> create(
            @ApiParam(value = "User object to create", required = true)
            @Valid
            @RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a new User by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved User"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<User> getOne(
            @ApiParam(value = "Id of the User to be retrieved", required = true)
            @PathVariable String id)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @GetMapping(value = "/email", params = {"email"})
    @ApiOperation(value = "Retrieve a new User by email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved User"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<User> getOneByEmail(@RequestParam("email") String email) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
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
        final User updatedUser = userService.findUserById(user.getId())
                .map(existingUser -> userService.saveUser(user))
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a User by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the User"),
            @ApiResponse(code = 404, message = "User not found")
    })
    ResponseEntity<String> deleteById(
            @ApiParam(value = "Id of the User to be deleted", required = true)
            @PathVariable String id)
            throws UserNotFoundException {
        userService.deleteUserById(id);
        return ResponseEntity.ok("deleted");
    }
}
