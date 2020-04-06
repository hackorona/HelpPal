package com.helpal.Main.api.request;

import com.helpal.model.request.Request;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping("/requests")
@Api(value = "Requests API")
public class Requests {

    private RequestService requestService;

    public Requests(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @ApiOperation(value = "List all available Requests", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")})
    public List<Request> getAll() {
        return requestService.getAllRequests();
    }

    @GetMapping(value = "/distance", params = {"userId", "distance"})
    @ApiOperation(value = "List all available Requests from to a given distance of a given User",
            response = List.class)
    public List<Request> getAllWithinDistance(
            @RequestParam("userId") String userId,
            @RequestParam("distance") Double distance) {
        return requestService.getRequestsInProximity(userId, distance);
    }

    @PostMapping
    @ApiOperation(value = "Create a new Request")
    public Request create(@RequestBody Request request) {
        return requestService.saveRequest(request);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a Request by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Request"),
            @ApiResponse(code = 404, message = "Request not found")})
    public Request getOne(@PathVariable String id) {
        return requestService.getRequestById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    @PutMapping
    @ApiOperation(value = "Update Request's data by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Request's data"),
            @ApiResponse(code = 404, message = "Request not found")})
    public Request update(@RequestBody Request request) {
        return requestService.getRequestById(request.getId())
                .map(existingRequest -> requestService.saveRequest(request))
                .orElseThrow(() -> new RequestNotFoundException(request.getId()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Request by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the Request")})
    public ResponseEntity<String> delete(@PathVariable String id) {
        requestService.deleteRequest(id);

        return ResponseEntity.ok("deleted");
    }
}
