package com.helpal.Main.api.request;

import com.helpal.model.Request;
import com.helpal.model.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request saveRequest(Request request) {
        Request retVal = null;

        try {
            retVal = requestRepository.saveAndFlush(request);
        } catch (Exception e) {
            throw new RequestNotFoundException(e.getMessage());
        }
        return retVal;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<Request> getRequestById(String id){
        return requestRepository.findById(id);
    }

    public void deleteRequest(String id){
        requestRepository.deleteById(id);
    }

}
