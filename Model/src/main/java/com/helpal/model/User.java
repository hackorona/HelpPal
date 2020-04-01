package com.helpal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_profile")
@ApiModel(description = "User's details")
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "Auto generated User-Id")
    private Long id;

    @ApiModelProperty(notes = "User full-name")
    private String name;

    @ApiModelProperty(notes = "User email")
    private String email;

    @ApiModelProperty(notes = "User phone number")
    private String phoneNumber;

    @ApiModelProperty(notes = "User address")
    private String address;

    @OneToOne
    @ApiModelProperty(notes = "User location")
    private Coords coords;

    @ApiModelProperty(notes = "User picture")
    private byte[] image;

    @ApiModelProperty(notes = "User primary language")
    private Languages language;

    @ApiModelProperty(notes = "Allow SMS notifications")
    private boolean smsNotification;

    @ApiModelProperty(notes = "User request count")
    private long cases;

    @ApiModelProperty(notes = "Does user have a badge?")
    private boolean badge;

    @ApiModelProperty(notes = "User birth year")
    private int birthYear;

    @ApiModelProperty(notes = "User encrypted password")
    private String password;

    public User() {}
    public User(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public boolean isSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(boolean smsNotification) {
        this.smsNotification = smsNotification;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public boolean isBadge() {
        return badge;
    }

    public void setBadge(boolean badge) {
        this.badge = badge;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public LocalDateTime getLastStatusChanged() {
        return lastStatusChanged;
    }

    public void setLastStatusChanged(LocalDateTime lastStatusChanged) {
        this.lastStatusChanged = lastStatusChanged;
    }

    private long score;
    private LocalDateTime lastStatusChanged;

}