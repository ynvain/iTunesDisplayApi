package com.example.ivan.itunesdisplayapi;


import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//используется track model, потому что в json используется вложенный список
public class TrackModel
{
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;


    @SerializedName("results")
    @Expose
    private ArrayList<Track> tracks;

    public Integer getResultCount() {
        return resultCount;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }
}
