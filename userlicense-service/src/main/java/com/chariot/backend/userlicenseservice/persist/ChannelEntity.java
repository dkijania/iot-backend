package com.chariot.backend.userlicenseservice.persist;

import javax.persistence.*;

/**
 * Created by Dariusz Kijania on 7/23/2017.
 */
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    private String id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public ChannelEntity() {
    }

    public ChannelEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelEntity)) return false;

        ChannelEntity channel = (ChannelEntity) o;

        return getId().equals(channel.getId())
                && getName().equals(channel.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
