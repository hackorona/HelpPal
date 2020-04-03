package com.helpal.Main.api.request;

import com.helpal.model.Request;
import com.helpal.model.RequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class Requests {

    private final RequestRepository repository;

    public Requests(RequestRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Request> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Request create(@RequestBody Request request) {
        return repository.save(request);
    }

    @GetMapping("/{id}")
    public Request getOne(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    @PutMapping()
    public Request replace(@RequestBody Request request) {
        return repository.findById(request.getId())
                .map(existingRequest -> repository.save(request))
                .orElseThrow(() -> new RequestNotFoundException(request.getId()));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
