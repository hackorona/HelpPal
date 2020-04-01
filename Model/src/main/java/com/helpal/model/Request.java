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
    private boolean onlyPreviousHelpers = false;
    private LocalDateTime created;
    private RequestStatuses status;

    @OneToOne
    private User destProfile;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private User ownerProfile;

    @OneToOne
    private User responderProfile;

    private byte[] billPhoto;
    private byte[] bagsPhoto;
    private double purchaseSum = 0;

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cetegories getCategory() {
        return category;
    }

    public void setCategory(Cetegories category) {
        this.category = category;
    }

    public Priorities getPriority() {
        return priority;
    }

    public void setPriority(Priorities priority) {
        this.priority = priority;
    }

    public Coords getLocation() {
        return location;
    }

    public void setLocation(Coords location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnlyPreviousHelpers() {
        return onlyPreviousHelpers;
    }

    public void setOnlyPreviousHelpers(boolean onlyPreviousHelpers) {
        this.onlyPreviousHelpers = onlyPreviousHelpers;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public RequestStatuses getStatus() {
        return status;
    }

    public void setStatus(RequestStatuses status) {
        this.status = status;
    }

    public User getDestProfile() {
        return destProfile;
    }

    public void setDestProfile(User destProfile) {
        this.destProfile = destProfile;
    }

    public User getOwnerProfile() {
        return ownerProfile;
    }

    public void setOwnerProfile(User ownerProfile) {
        this.ownerProfile = ownerProfile;
    }

    public User getResponderProfile() {
        return responderProfile;
    }

    public void setResponderProfile(User responderProfile) {
        this.responderProfile = responderProfile;
    }

    public byte[] getBillPhoto() {
        return billPhoto;
    }

    public void setBillPhoto(byte[] billPhoto) {
        this.billPhoto = billPhoto;
    }

    public byte[] getBagsPhoto() {
        return bagsPhoto;
    }

    public void setBagsPhoto(byte[] bagsPhoto) {
        this.bagsPhoto = bagsPhoto;
    }

    public double getPurchaseSum() {
        return purchaseSum;
    }

    public void setPurchaseSum(double purchaseSum) {
        this.purchaseSum = purchaseSum;
    }
}