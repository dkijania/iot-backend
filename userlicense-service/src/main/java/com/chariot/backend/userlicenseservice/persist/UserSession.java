package com.chariot.backend.userlicenseservice.persist;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_session")
public class UserSession {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="USER_ID",referencedColumnName = "id")
    private UserEntity user;

   private Date expiryDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
