package com.chariot.backend.model;

import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/23/2017.
 */
public class Channel {
    private String name;
    private UUID id;

    public Channel() {
    }

    public Channel(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
