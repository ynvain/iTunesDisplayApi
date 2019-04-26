package com.example.ivan.itunesdisplayapi;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//описывается поиск списка альбомов по запросу
public class Presenter implements MainContract.Presenter {

    private MainContract.View mView;

    private String term;
    private String entity;
    private ArrayList<Album> albums;


    // через mView презентер работает с активити
    public Presenter(MainContract.View mView) {
        this.mView = mView;

    }
    //приводит поисковый запрос пользователя к виду url
    private String transformRequest(String searchRequest)
    {
        searchRequest = searchRequest.replaceAll(" ", "+").toLowerCase();
        return searchRequest;
    }

    //метод c помощью структур данных и retrofit по поисковому запросу и создает список альбомов
    @Override
    public void getAlbumsList(String searchRequest){


        term = transformRequest(searchRequest);
        entity = "album";

        JsonApi jsonApi = ApiService.getInstance();

        Call<AlbumModel> call = jsonApi.getAlbums(term, entity);

        call.enqueue(new Callback<AlbumModel>() {
            @Override
            public void onResponse(Call<AlbumModel> call, Response<AlbumModel> response) {

                AlbumModel albumModel = response.body();

                if (albumModel.getResultCount() > 0) {
                    albums = albumModel.getAlbums();

                    albums = sortAlbums(albums);

                    mView.displayAlbums(albums);
                } else mView.displayMessage("No albums found, Try again");


            }

            @Override
            public void onFailure(Call<AlbumModel> call, Throwable t) {
                mView.displayMessage(t.getMessage());
            }

    });
}

    //первое активити вызывает этот метод и передает поисковой запрос
    @Override
    public void onSearchButtonClicked(String searchRequest)
    {
        if(searchRequest.length()!= 0)
        {
            getAlbumsList(searchRequest);
        }
        else mView.displayMessage("Please type something, the field is empty!");
    }

    //метод переходит ко второму активити после выбора конкретного альбома
    @Override
    public void lookUpAlbumInfo(int position, Context context)
    {
        Album album = albums.get(position);

        Intent intent = new Intent(context, AlbumInfoActivity.class);

        //сериализация объекта выбранного альбома для передачи его другому активити

        intent.putExtra("album", album);
        context.startActivity(intent);

    }

    //метод сортирует список альбомов в алфавитном порядке
    private ArrayList<Album> sortAlbums(ArrayList<Album> albums)
    {
        Collections.sort(albums, new Comparator<Album>() {
            @Override
            public int compare(final Album object1, final Album object2) {
                return object1.getCollectionName().compareTo(object2.getCollectionName());
            }
        });
        return albums;
    }



}


