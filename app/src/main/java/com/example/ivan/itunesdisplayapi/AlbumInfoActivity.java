package com.example.ivan.itunesdisplayapi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Map;

//описывается представления информации о конкретном альбоме
public class AlbumInfoActivity extends AppCompatActivity implements MainContract.AlbumView
{

    private RecyclerView mRecyclerViewTracks;
    private TrackAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private ImageView imageViewAlbum;

    private TextView textViewAlbum;
    private TextView textViewArtist;
    private TextView textViewGenre;
    private TextView textViewYear;
    private TextView textViewPrice;

    private ArrayList<Track> tracks = new ArrayList<>();


    private MainContract.AlbumPresenter mAlbumPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        //десериализация объекта
        Album album = getIntent().getParcelableExtra("album");

        //активити передает свой контекст и объект адьбома презентеру
        mAlbumPresenter = new AlbumInfoPresenter(this, album);

        imageViewAlbum = findViewById(R.id.imageViewAlbumCover);

        textViewAlbum = findViewById(R.id.textViewAlbum);
        textViewArtist = findViewById(R.id.textViewArtist);
        textViewGenre = findViewById(R.id.textViewGenre);
        textViewYear = findViewById(R.id.textViewYear);
        textViewPrice = findViewById(R.id.textViewPrice);


        mRecyclerViewTracks = findViewById(R.id.recyclerViewTracks);
        mRecyclerViewTracks.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new TrackAdapter(tracks);
        mRecyclerViewTracks.setLayoutManager(mLayoutManager);
        mRecyclerViewTracks.setAdapter(mAdapter);

        //при создании активити сразу вызывает метод презентера для заполнеия информации
        mAlbumPresenter.setInfo();

    }


    @Override
    public void displayAlbumInfo(Map<String, String> hashMap)
    {
        //загрузка изображения по url

        String artworkUrl100 = hashMap.get("artworkUrl100");
        Glide.with(this).load(artworkUrl100).into(imageViewAlbum);

        textViewAlbum.setText(hashMap.get("album"));
        textViewArtist.setText(hashMap.get("artist"));
        textViewGenre.setText(hashMap.get("genre"));
        textViewYear.setText(hashMap.get("year"));
        textViewPrice.setText(hashMap.get("price")+"$");


    }

    @Override
    public void displayTracks(ArrayList<Track> tracks)
    {
        this.tracks.clear();
        this.tracks.addAll(tracks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayMessage(String message) {

        Toast toast;
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();

    }
}
