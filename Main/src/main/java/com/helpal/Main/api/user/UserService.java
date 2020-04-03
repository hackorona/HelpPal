package com.helpal.Main.api.user;

import com.helpal.model.User;
import com.helpal.model.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        User retVal = null;
        try {
            retVal = repository.saveAndFlush(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserNotCreatedException(e.getMessage());
        }
        return retVal;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> findUserById(String id){
        return repository.findById(id);
    }

    public Optional<User> findUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public void deleteUserById(String id) {
        repository.deleteById(id);
    }
}
