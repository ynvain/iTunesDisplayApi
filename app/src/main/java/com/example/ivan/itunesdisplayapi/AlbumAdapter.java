package com.example.ivan.itunesdisplayapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<Album> mAlbumList;
    private OnItemClickListener mListener;

    private Context mContext;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public AlbumViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
           mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textViewArtist);
            mTextView3 = itemView.findViewById(R.id.textViewGenre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AlbumAdapter(ArrayList<Album> albumList, Context context) {
        mAlbumList = albumList;
        mContext = context;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        AlbumViewHolder avh = new AlbumViewHolder(v, mListener);
        return avh;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album currentItem = mAlbumList.get(position);

        //загрузка изображения для конкретного альбома по url
        String artworkUrl = currentItem.getArtworkUrl100();
        Glide.with(mContext).load(artworkUrl).into(holder.mImageView);

        holder.mTextView1.setText(currentItem.getCollectionName());
        holder.mTextView2.setText(currentItem.getArtistName());
        holder.mTextView3.setText(currentItem.getPrimaryGenreName());
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }
}


