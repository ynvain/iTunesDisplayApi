package com.example.ivan.itunesdisplayapi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

//описывается представление списка альбомов и строка поиска
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private Button searchButton;
    private EditText searchEditText;
    private ArrayList<Album> albums = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        searchButton = findViewById(R.id.buttonSearch);
        searchEditText = findViewById(R.id.editText);

        //создание объекта презентера для того, чтобы передать контекст активити


        mPresenter = new Presenter(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchRequest = searchEditText.getText().toString();
                mPresenter.onSearchButtonClicked(searchRequest);
                closeKeyboard();

            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new AlbumAdapter(albums,mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.INVISIBLE);

        closeKeyboard();

        mAdapter.setOnItemListener(new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Album album = albums.get(position);
                mPresenter.lookUpAlbumInfo(album, mContext);
            }
        });



    }

    //Метод отображает альбомы
    @Override
    public void displayAlbums(ArrayList<Album> albums, boolean goToTop)
    {
        this.albums.clear();
        this.albums.addAll(albums);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);

        if(goToTop) mRecyclerView.smoothScrollToPosition(0);

    }

    //выводит сообщение об ошибке или отстутсвии результатов
    @Override
    public void displayMessage(String message){

        Toast toast;
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();

    }

    //метод сворачиват клавиатуру после нажатия кнопки поиска
    private void closeKeyboard(){
        View view = getCurrentFocus();
        InputMethodManager manager = (InputMethodManager) getSystemService(mContext.INPUT_METHOD_SERVICE);
        if(manager.isAcceptingText()){
            manager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    //сохранение списка с альбомами
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("array", albums);
        super.onSaveInstanceState(outState);

    }
    //восстановление списка
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

            ArrayList<Album> tempArray = savedInstanceState.getParcelableArrayList("array");
            displayAlbums(tempArray,false);



    }



}
