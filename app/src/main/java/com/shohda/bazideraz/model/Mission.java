package com.shohda.bazideraz.model;

public class Mission {
    private String id, mission_name,date,Location, content, mission_image,mission_imagelarge,commanders;
    public Mission() {
    }
    public Mission(String id, String mission_name,String Location,String content, String mission_image,String mission_imagelarge,String commanders) {
        this.id = id;
        this.mission_name = mission_name;
        this.date = date;
        this.Location = Location;
        this.content = content;
        this.mission_image = mission_image;
        this.mission_imagelarge = mission_imagelarge;
        this.commanders = commanders;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMission_image() {
        return mission_image;
    }

    public void setMission_image(String mission_image) {
        this.mission_image = mission_image;
    }

    public String getMission_imagelarge() {
        return mission_imagelarge;
    }

    public void setMission_imagelarge(String mission_imagelarge) {
        this.mission_imagelarge = mission_imagelarge;
    }

    public String getCommanders() {
        return commanders;
    }

    public void setCommanders(String commanders) {
        this.commanders = commanders;
    }
}
