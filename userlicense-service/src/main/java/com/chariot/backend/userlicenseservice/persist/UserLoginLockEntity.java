package com.chariot.backend.userlicenseservice.persist;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_lock")
public class UserLoginLockEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID",referencedColumnName = "id")
    private UserEntity user;

    private Date timeStamp;

    private boolean isSuccessful;

    private int unsuccessfulTry;

    private boolean isLocked;

    private Date nextLoginPossible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getNextLoginPossible() {
        return nextLoginPossible;
    }

    public void setNextLoginPossible(Date nextLoginPossible) {
        this.nextLoginPossible = nextLoginPossible;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public int getUnsuccessfulTry() {
        return unsuccessfulTry;
    }

    public void setUnsuccessfulTry(int unsuccessfulTry) {
        this.unsuccessfulTry = unsuccessfulTry;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
