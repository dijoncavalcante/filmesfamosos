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

    public RecyclerViewAdapter(Context context, List<Result> resultList) {
        this.resultList = resultList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.name.setText(resultList.get(position).getTitle());
        holder.rattingBar.setText(String.valueOf(resultList.get(position).getVote_average()));
        String urlDaImagem = "https://image.tmdb.org/t/p/w185"+resultList.get(position).getPoster_path();
        Picasso.with(context)
                .load(urlDaImagem)
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.drawable.ic_menu_camera)
                .into(holder.foto);
        holder.foto.setContentDescription(urlDaImagem);
        holder.tvDescricaoSinopse.setText(resultList.get(position).getOverview());
        holder.tvLancamentoValor.setText(resultList.get(position).getRelease_date());
        holder.tvVotosValor.setText(String.valueOf(resultList.get(position).getVote_count()));
    }

    @Override
    public int getItemCount() {
        return this.resultList.size();
    }
}
