package com.example.micuna.modelo;

import java.io.Serializable;

public class Cliente{
    String id;
    String name;
    String email;
    String image;


    public Cliente() {

    }
    public Cliente(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Cliente(String id, String name, String email, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
