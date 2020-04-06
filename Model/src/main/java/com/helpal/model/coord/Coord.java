package com.helpal.model.coord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpal.model.request.Request;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "coord")
public class Coord implements Serializable {
    @Id
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JoinTable
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Request> requests = new HashSet<>();

    private double x = 0;

    private double y = 0;

    public Coord() {
        this.id = UUID.randomUUID().toString();
    }

    public Coord(String id) {
        this.id = id;
    }

    public Coord(Double lat, Double lon) {
        this.id = UUID.randomUUID().toString();
        this.x = lon;
        this.y = lat;
    }

    public Coord(Set<Request> requests, double x, double y) {
        this();
        this.requests = requests;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Double.compare(coord.x, x) == 0 &&
                Double.compare(coord.y, y) == 0 &&
                Objects.equals(id, coord.id) &&
                Objects.equals(requests, coord.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requests, x, y);
    }
}