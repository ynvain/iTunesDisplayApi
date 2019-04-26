package com.example.ivan.itunesdisplayapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track
{

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTime;


    public String getTrackName() {
        return trackName;
    }

    public Integer getTrackTime()
    {
        return trackTime;
    }


}
