package com.helpal.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpal.model.coord.Coord;
import com.helpal.model.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "request")
@ApiModel(description = "Request's model")
public class Request implements Serializable {

    @Id
    @ApiModelProperty(notes = "Auto generated Request-Id")
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @ApiModelProperty(notes = "Category")
    private Categories category = Categories.SUPERMARKET;

    @ApiModelProperty(notes = "Priority")
    private Priority priority = Priority.NONE;

    @ManyToOne(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Coordinates")
    private Coord coord;

    @ApiModelProperty(notes = "Request description")
    private String description;

    @ApiModelProperty(notes = "Should request only be opened to previous helpers")
    private boolean onlyPreviousHelpers = false;

    @ApiModelProperty(notes = "Request creation time")
    private LocalDateTime created = LocalDateTime.now();

    @ApiModelProperty(notes = "Request status")
    private RequestStatus status = RequestStatus.OPEN;

    @ApiModelProperty(notes = "Request's destination user profile")
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User destProfile;

    @ApiModelProperty(notes = "Request creator user profile", required = true)
    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private User ownerProfile;

    @ApiModelProperty(notes = "3rd Party User profile")
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User responderProfile;

    @ApiModelProperty(notes = "Shopping bag receipt's photo")
    private byte[] billPhoto;

    @ApiModelProperty(notes = "Shopping bag photo")
    private byte[] bagsPhoto;

    @ApiModelProperty(notes = "Purchase amount")
    private Double purchaseSum = 0D;

    public Request() {
        this.id = UUID.randomUUID().toString();
    }

    public Request(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord location) {
        this.coord = location;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return onlyPreviousHelpers == request.onlyPreviousHelpers &&
                Double.compare(request.purchaseSum, purchaseSum) == 0 &&
                Objects.equals(id, request.id) &&
                category == request.category &&
                priority == request.priority &&
                Objects.equals(coord, request.coord) &&
                Objects.equals(description, request.description) &&
                Objects.equals(created, request.created) &&
                status == request.status &&
                Objects.equals(destProfile, request.destProfile) &&
                Objects.equals(ownerProfile, request.ownerProfile) &&
                Objects.equals(responderProfile, request.responderProfile) &&
                Arrays.equals(billPhoto, request.billPhoto) &&
                Arrays.equals(bagsPhoto, request.bagsPhoto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, category, priority, coord, description, onlyPreviousHelpers, created, status
                , destProfile, ownerProfile, responderProfile, purchaseSum);
        result = 31 * result + Arrays.hashCode(billPhoto);
        result = 31 * result + Arrays.hashCode(bagsPhoto);
        return result;
    }
}