package com.helpal.model.coord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpal.model.request.Request;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.impl.PointImpl;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "coord")
public class Coord {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Request> requests;

    private double x;

    private double y;

    public Coord() {
        this.id = UUID.randomUUID().toString();
    }

    public Coord(Double lat, Double lon) {
        this.id = UUID.randomUUID().toString();
        this.x=lon;
        this.y=lat;
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
}