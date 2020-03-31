package com.helpal.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    private Cetegories category;
    private Priorities priority;

    @OneToOne
    private Coords location;

    private String description;
    private boolean onlyPreviousHelpers;
    private LocalDateTime created;
    private RequestStatuses status;

    @OneToOne
    private User destProfile;

    @OneToOne
    @JoinColumn(nullable = false)
    private User ownerProfile;

    @OneToOne
    private User responderProfile;

    private byte[] billPhoto;
    private byte[] bagsPhoto;
    private double purchaseSum;

    public Request() {}
}