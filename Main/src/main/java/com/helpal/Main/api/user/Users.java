package com.helpal.Main.api.user;

import com.helpal.model.User;
import com.helpal.model.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Users {

    private final UserRepository repository;

    public Users(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAll(){
        return repository.findAll();
    }

    @PostMapping()
    public User create(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping()
    public User replace(@RequestBody User user) {
        return repository.findById(user.getId())
            .map(existingUser -> repository.save(user))
            .orElseThrow(() -> new UserNotFoundException(user.getId()));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
