package com.helpal.Main.api.request;

import com.helpal.Main.api.user.UserNotFoundException;
import com.helpal.model.coord.Coord;
import com.helpal.model.coord.CoordRepository;
import com.helpal.model.request.Request;
import com.helpal.model.request.RequestRepository;
import com.helpal.model.user.User;
import com.helpal.model.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RequestService {

    private RequestRepository requestRepository;
    private CoordRepository coordRepository;
    private UserRepository userRepository;

    public RequestService(
            RequestRepository requestRepository,
            CoordRepository coordRepository,
            UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.coordRepository = coordRepository;
        this.userRepository = userRepository;
    }

    public Request saveRequest(Request request) {
        Request retVal = null;

        try {
            retVal = requestRepository.save(request);
        } catch (Exception e) {
            throw new RequestNotFoundException(e.getMessage());
        }
        return retVal;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<Request> getRequestById(String id) {
        return requestRepository.findById(id);
    }

    public void deleteRequest(String id) {
        requestRepository.deleteById(id);
    }

    public List<Request> getRequestsInProximity(String userId, Double distance) {

        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        Coord centerLocation = user.get().getCoord();
        List<Coord> allCoordsWithinDistance =
                coordRepository.findAllCoordsWithinDistance(
                        centerLocation
                        , distance);

        Set<Request> requests = new HashSet<>();

        for (Coord curCoord : allCoordsWithinDistance) {
            requests.addAll(curCoord.getRequests());
        }

        return new ArrayList<>(requests);
    }
}
