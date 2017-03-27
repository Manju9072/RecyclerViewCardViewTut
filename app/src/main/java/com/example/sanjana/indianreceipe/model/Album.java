package com.example.sanjana.indianreceipe.model;

import java.net.URL;

/**
 * Created by sanjana on 20/3/17.
 */

public class Album {

    private String name;
    private int numOfSongs;
    private int thumbnail;
    private String url;

    public Album(String name, int numOfSongs, int thumbnail, String url) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
