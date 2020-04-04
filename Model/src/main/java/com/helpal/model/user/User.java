package com.helpal.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helpal.model.coord.Coord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_profile", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@ApiModel(description = "User's model")
public class User implements Serializable {

    @Id
    @ApiModelProperty(notes = "Auto generated User-Id")
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @ApiModelProperty(notes = "User full-name")
    private String name;

    @ApiModelProperty(notes = "User email", required = true)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ApiModelProperty(notes = "User phone number")
    private String phoneNumber;

    @ApiModelProperty(notes = "User address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "User location")
    private Coord coord;

    @ApiModelProperty(notes = "User picture")
    private byte[] image;

    @ApiModelProperty(notes = "User primary language")
    private Languages language = Languages.ENGLISH;

    @ApiModelProperty(notes = "Allow SMS notifications")
    private boolean smsNotification = true;

    @ApiModelProperty(notes = "User request count")
    private Long cases;

    @ApiModelProperty(notes = "Does user have a badge?")
    private boolean badge;

    @ApiModelProperty(notes = "User birth year")
    private int birthYear;

    @ApiModelProperty(notes = "User encrypted password")
    private String password;

    @ApiModelProperty(notes = "User credibility score")
    private Long score = 0L;

    @ApiModelProperty(notes = "Last status chage time")
    private LocalDateTime lastStatusChanged = LocalDateTime.now();

    @ApiModelProperty(notes = "Is the User a confirmed Covid-19 patient")
    private Boolean isConfirmedCovid19 = false;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
//    public User(String id){
//        this.id = id;
//    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
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

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public LocalDateTime getLastStatusChanged() {
        return lastStatusChanged;
    }

    public void setLastStatusChanged(LocalDateTime lastStatusChanged) {
        this.lastStatusChanged = lastStatusChanged;
    }

    public void setCases(Long cases) {
        this.cases = cases;
    }

    public Boolean getConfirmedCovid19() {
        return isConfirmedCovid19;
    }

    public void setConfirmedCovid19(Boolean confirmedCovid19) {
        isConfirmedCovid19 = confirmedCovid19;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return smsNotification == user.smsNotification &&
                badge == user.badge &&
                birthYear == user.birthYear &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(address, user.address) &&
                Objects.equals(coord, user.coord) &&
                Arrays.equals(image, user.image) &&
                language == user.language &&
                Objects.equals(cases, user.cases) &&
                Objects.equals(password, user.password) &&
                Objects.equals(score, user.score) &&
                Objects.equals(lastStatusChanged, user.lastStatusChanged) &&
                Objects.equals(isConfirmedCovid19, user.isConfirmedCovid19);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, email, phoneNumber, address, coord, language, smsNotification, cases,
                badge, birthYear, password, score, lastStatusChanged, isConfirmedCovid19);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", coord=" + coord +
                ", image=" + Arrays.toString(image) +
                ", language=" + language +
                ", smsNotification=" + smsNotification +
                ", cases=" + cases +
                ", badge=" + badge +
                ", birthYear=" + birthYear +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", lastStatusChanged=" + lastStatusChanged +
                ", isConfirmedCovid19=" + isConfirmedCovid19 +
                '}';
    }
}