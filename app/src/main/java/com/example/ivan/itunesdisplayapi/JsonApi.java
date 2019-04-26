package com.example.ivan.itunesdisplayapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface JsonApi
{
    // запрос для поиска альбомов
    @GET("search")
    Call<AlbumModel> getAlbums(@Query("term") String term, @Query("entity") String entity);

    // запрос для вывода треков из кокретного альбома по его id
    @GET("lookup")
    Call<TrackModel> getTracks(@Query("id") int id, @Query("entity") String entity);

}
