package com.sistemas.braga.filmesfamosos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.Result;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Result> resultList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Result> resultListlt) {
        this.resultList = resultListlt;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        //holder.name.setText(resultList.get(position).getTitle());
        //holder.rattingBar.setRating(resultList.get(position).getVote_average());
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w185" + resultList.get(position).getPoster_path())
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.drawable.ic_menu_camera)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return this.resultList.size();
    }
}
