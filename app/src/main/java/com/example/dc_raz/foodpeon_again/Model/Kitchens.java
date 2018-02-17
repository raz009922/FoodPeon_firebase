package com.example.dc_raz.foodpeon_again.Model;

/**
 * Created by RAZ on 06-Feb-18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kitchens {
//    public Kitchens(String name, String realname, String team, String firstappearance, String createdby, String publisher, String imageurl, String bio) {
//        this.name = name;
//        this.realname = realname;
//        this.team = team;
//        this.firstappearance = firstappearance;
//        this.createdby = createdby;
//        this.publisher = publisher;
//        this.imageurl = imageurl;
//        this.bio = bio;
//    }


    public Kitchens(String name, String imageurl, String bio) {
        this.name = name;
        this.imageurl = imageurl;
        this.bio = bio;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("realname")
    @Expose
    private String realname;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("firstappearance")
    @Expose
    private String firstappearance;
    @SerializedName("createdby")
    @Expose
    private String createdby;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    @SerializedName("bio")
    @Expose
    private String bio;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public void setFirstappearance(String firstappearance) {
        this.firstappearance = firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
