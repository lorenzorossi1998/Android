package com.classes.objects;

import java.util.ArrayList;

public class Armadio {
    String username;
    ArrayList<Capo> capi;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Capo> getCapi() {
        return capi;
    }

    public void setCapi(ArrayList<Capo> capi) {
        this.capi = capi;
    }

    @Override
    public String toString() {
        return "Armadio{" +
                "username='" + username + '\'' +
                ", capi=" + capi.toString() +
                '}';
    }
}
