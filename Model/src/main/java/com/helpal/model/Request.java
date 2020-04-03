package com.helpal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "request")
@ApiModel(description = "Request's model")
public class Request {

    @Id
    @ApiModelProperty(notes = "Auto generated Request-Id")
    private String id;

    @ApiModelProperty(notes = "Category")
    private Cetegories category;

    @ApiModelProperty(notes = "Priority")
    private Priority priority;

    @OneToOne
    @ApiModelProperty(notes = "Coordinates")
    private Coords location;

    @ApiModelProperty(notes = "Request description")
    private String description;

    @ApiModelProperty(notes = "Should request only be opened to previous helpers")
    private boolean onlyPreviousHelpers = false;

    @ApiModelProperty(notes = "User full-name")
    private LocalDateTime created;

    @ApiModelProperty(notes = "Request status")
    private RequestStatus status;

    @ApiModelProperty(notes = "Request's destination user profile")
    @OneToOne
    private User destProfile;

    @ApiModelProperty(notes = "Request creator user profile", required = true)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private User ownerProfile;

    @ApiModelProperty(notes = "3rd Party User profile")
    @OneToOne
    private User responderProfile;

    @ApiModelProperty(notes = "Shopping bag receipt's photo")
    private byte[] billPhoto;

    @ApiModelProperty(notes = "Shopping bag photo")
    private byte[] bagsPhoto;

    @ApiModelProperty(notes = "Purchase amount")
    private double purchaseSum = 0D;

    public Request() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Cetegories getCategory() {
        return category;
    }

    public void setCategory(Cetegories category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
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