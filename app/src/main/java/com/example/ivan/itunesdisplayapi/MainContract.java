package com.example.ivan.itunesdisplayapi;


import android.content.Context;
import java.util.ArrayList;
import java.util.Map;

//интерфейс частично реализует mvp
public interface MainContract {

    //представление для отображения альбомов
    interface View
    {
        void displayAlbums(ArrayList<Album> albums, boolean goToTop);
        void displayMessage(String message);
    }
    //"логика" для отображения альбомов
    interface Presenter
    {

        void getAlbumsList(String searchRequest);
        void onSearchButtonClicked(String searchResult);
        void lookUpAlbumInfo(Album album, Context context);

    }
    //представление для отображения конкретного альбома
    interface AlbumView
    {
        void displayAlbumInfo(Map<String, String> hashMap);
        void displayTracks(ArrayList<Track> tracks);
        void displayMessage(String message);


    }
    //"логика" для отображения конкретного альбома
    interface AlbumPresenter
    {
        void setInfo();
        void getTracksList();
        void setAlbumsInfo();
    }






}
