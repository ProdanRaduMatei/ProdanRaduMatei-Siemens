package com.example.domain;

import java.util.List;

public class Hotel {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;

    public Hotel() {
        this.id = 0;
        this.name = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Hotel(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "Hotel [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", rooms=" + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hotel other = (Hotel) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int hashCode() {
        return id;
    }
}
