package com.helpal.Main.api.request;

import com.helpal.model.request.Request;
import com.helpal.model.user.User;
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

import java.util.List;

@RestController
@RequestMapping("/requests")
public class Requests {

    private RequestService requestService;

    public Requests(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<Request> getAll() {
        return requestService.getAllRequests();
    }

    @GetMapping(name = "/distance", params = {"userId", "distance"})
    public List<Request> getAllWithinDistance(
            @RequestParam("userId") String userId,
            @RequestParam("distance") Double distance) {
        return requestService.getRequestsInProximity(userId, distance);
    }

    @PostMapping
    public Request create(@RequestBody Request request) {
        return requestService.saveRequest(request);
    }

    @GetMapping("/{id}")
    public Request getOne(@PathVariable String id) {
        return requestService.getRequestById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    @PutMapping
    public Request update(@RequestBody Request request) {
        return requestService.getRequestById(request.getId())
                .map(existingRequest -> requestService.saveRequest(request))
                .orElseThrow(() -> new RequestNotFoundException(request.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        requestService.deleteRequest(id);

        return ResponseEntity.ok("deleted");
    }
}
