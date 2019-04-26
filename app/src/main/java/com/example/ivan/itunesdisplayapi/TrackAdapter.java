package com.example.ivan.itunesdisplayapi;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>
{
    private ArrayList<Track> mTrackList;

    public static class TrackViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewTrack;
        public TextView mTextViewTime;
        public TextView mTextViewNumber;


        public TrackViewHolder(View itemView) {
            super(itemView);

            mTextViewTrack = itemView.findViewById(R.id.textViewTrack);
            mTextViewTime = itemView.findViewById(R.id.textViewTime);
            mTextViewNumber = itemView.findViewById(R.id.textViewNumber);

        }
    }

    public TrackAdapter(ArrayList<Track> trackList) {
        mTrackList = trackList;
    }

    @Override
    public TrackAdapter.TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        TrackAdapter.TrackViewHolder tvh = new TrackAdapter.TrackViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TrackAdapter.TrackViewHolder holder, int position) {
        Track currentItem = mTrackList.get(position);

        String transformedTime = transformTrackTime(currentItem.getTrackTime());

        //используем это значение, чтобы отображать номер трека в списке
        String i = String.valueOf(position+1);

        holder.mTextViewNumber.setText(i);
        holder.mTextViewTrack.setText(currentItem.getTrackName());
        holder.mTextViewTime.setText(transformedTime);

    }

    //метод переводит время трека из миллиекунд в формат минут и секунд
    private String transformTrackTime(Integer trackTimeMillis)
    {
        Integer minutes = (trackTimeMillis/1000)/60;
        Integer seconds = (trackTimeMillis/1000)%60;

        String transformedTime = String.format("%d:%02d", minutes, seconds);

        return transformedTime;

    }

    @Override
    public int getItemCount() {
        return mTrackList.size();
    }
}

