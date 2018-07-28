package com.sistemas.braga.filmesfamosos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import model.Result;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public ImageView foto;
    public TextView  rattingBar;
    public TextView tvVotosValor;
    public TextView tvLancamentoValor;
    public TextView tvDescricaoSinopse;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name = itemView.findViewById(R.id.texteView_name);
        foto = itemView.findViewById(R.id.imageView_foto);
        rattingBar = itemView.findViewById(R.id.ratingBar);
        tvVotosValor = itemView.findViewById(R.id.tvVotosValor);
        tvLancamentoValor = itemView.findViewById(R.id.tvLancamentoValor);
        tvDescricaoSinopse = itemView.findViewById(R.id.tvDescricaoSinopse);
    }

    @Override
    public void onClick(View v) {
        Result filme = new Result();
        filme.setTitle(name.getText().toString());
        filme.setPoster_path(foto.getContentDescription().toString());
        filme.setVote_average(Float.parseFloat(String.valueOf(rattingBar.getText())));
        filme.setOverview(tvDescricaoSinopse.getText().toString());
        filme.setRelease_date(tvLancamentoValor.getText().toString());
        filme.setVote_count(Float.parseFloat(String.valueOf(tvVotosValor.getText())));

        Bundle bundle = new Bundle();
        bundle.putSerializable("filme", filme);
        Intent intent = new Intent(v.getContext(), DetalheActivity.class);
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }
}
