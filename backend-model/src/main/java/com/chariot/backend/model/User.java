package com.chariot.backend.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
public class User {
    private String name;
    private String password;
    private License license;
    private final List<Channel> channels;

    public User(String name, String password, License license) {
        this();
        this.name = name;
        this.password = password;
        this.license = license;
    }

    public User() {
        channels = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public boolean remove(Channel channel) {
        return channels.remove(channel);
    }

    public List<Channel> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
