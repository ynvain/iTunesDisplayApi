package com.example.ivan.itunesdisplayapi;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//используется album model, потому что в json используется вложенный список
public class AlbumModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("results")
    @Expose
    private ArrayList<Album> albums = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
