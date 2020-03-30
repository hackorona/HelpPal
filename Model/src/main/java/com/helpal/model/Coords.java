package com.helpal.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "COORDS")
public class Coords {
    @Id
    @GeneratedValue
    private long id;
    private double lat;
    private double lon;

    public Coords(){}

    public Coords(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}