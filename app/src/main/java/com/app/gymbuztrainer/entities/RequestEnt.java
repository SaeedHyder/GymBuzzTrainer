package com.app.gymbuztrainer.entities;

/**
 * Created on 5/29/2018.
 */

public class RequestEnt {

    String name = "";

    public RequestEnt(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
