package com.example.ivan.itunesdisplayapi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//описывается вывод список треков и детальная информация о выбранном альбоме
public class AlbumInfoPresenter implements MainContract.AlbumPresenter
{
    private MainContract.AlbumView mView;
    private ArrayList<Track> tracks;
    private Album currentAlbum;

    //презентер черз mView удет работать со вторым активити
    public AlbumInfoPresenter(MainContract.AlbumView View, Album album) {

        currentAlbum = album;
        mView = View;
    }

    @Override
    public void setInfo()
    {
        getTracksList();
        setAlbumsInfo();
    }

    //метод отображает треки по запросу для конкретного альбома
    @Override
    public void getTracksList()
    {
        int id = Integer.parseInt(currentAlbum.getCollectionId());

        String entity = "song";

        JsonApi jsonApi = ApiService.getInstance();

        Call<TrackModel> call = jsonApi.getTracks(id, entity);

        call.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(Call<TrackModel> call, Response<TrackModel> response) {

                TrackModel trackModel = response.body();
                    tracks = trackModel.getTracks();
                    tracks.remove(0);
                    mView.displayTracks(tracks);
            }

            @Override
            public void onFailure(Call<TrackModel> call, Throwable t) {
                mView.displayMessage(t.getMessage());
            }

        });


    }


    @Override
    public void setAlbumsInfo()
    {
        Map<String, String> hashMap = new HashMap<String, String>();

        String year = getYear(currentAlbum.getReleaseDate());

        //в мап помещается вся необходимая информация об альмбоме
        hashMap.put("album",currentAlbum.getCollectionName());
        hashMap.put("artist",currentAlbum.getArtistName());
        hashMap.put("genre",currentAlbum.getPrimaryGenreName());
        hashMap.put("artworkUrl100",currentAlbum.getArtworkUrl100());
        hashMap.put("year",year);
        hashMap.put("price",currentAlbum.getCollectionPrice());

        //мап передается активити для отображения информации
        mView.displayAlbumInfo(hashMap);

    }

    //метод убирает из даты все, кроме года выпуска
    private String getYear(String date)
    {
            return date.substring(0,4);
    }






}

