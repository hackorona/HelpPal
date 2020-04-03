package com.helpal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "COORDS")
public class Coords {
    @Id
    private String id;
    private Double lat;
    private Double lon;

    public Coords(){
        this.id = UUID.randomUUID().toString();
    }

    public Coords(double lat, double lon) {
        this();
        this.lat = lat;
        this.lon = lon;
    }
}