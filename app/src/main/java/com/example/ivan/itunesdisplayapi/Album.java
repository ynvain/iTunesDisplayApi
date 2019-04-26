package com.example.ivan.itunesdisplayapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable{

    @SerializedName("collectionId")
    @Expose
    private String collectionId;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("collectionName")
    @Expose
    private String collectionName;

    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;


    @SerializedName("collectionPrice")
    @Expose
    private String collectionPrice;


    public String getCollectionId() {
        return collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    //сериализация объекта
    public Album(Parcel in){
        String[] data= new String[7];

        in.readStringArray(data);
        this.collectionId= data[0];
        this.artistName= data[1];
        this.collectionName= data[2];
        this.artworkUrl100= data[3];
        this.primaryGenreName= data[4];
        this.releaseDate= data[5];
        this.collectionPrice= data[6];

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[]{this.collectionId,this.artistName,this.collectionName,this.artworkUrl100,this.primaryGenreName,this.releaseDate,this.collectionPrice});
    }

    public static final Parcelable.Creator<Album> CREATOR= new Parcelable.Creator<Album>() {

        @Override
        public Album createFromParcel(Parcel source) {

            return new Album(source);  //using parcelable constructor
        }

        @Override
        public Album[] newArray(int size) {

            return new Album[size];
        }
    };
}
