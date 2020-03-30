package com.helpal.model;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "user_profile")
public class User {

    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    @OneToOne
    private Coords coords;
    private byte[] image;

    private Languages language;
    private boolean smsNotification;
    private long cases;
    private boolean badge;
    private int birthYear;
    private String password;
    private long score;

    public User() {}
}