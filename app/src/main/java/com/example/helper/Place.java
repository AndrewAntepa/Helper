package com.example.helper;

public class Place {
    public String id, desc;
    public double lat, lon;

    public Place(){

    }

    public Place(String id, String desc, double lat, double lon) {
        this.id = id;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
    }
}
