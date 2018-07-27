package com.sistemas.braga.filmesfamosos;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name;
    public ImageView foto;
    public RatingBar rattingBar;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        //name = itemView.findViewById(R.id.imageView_name);
        foto = itemView.findViewById(R.id.imageView_foto);
       // rattingBar = itemView.findViewById(R.id.ratingBar);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Foto clicada, posição = " + getPosition(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(v.getContext(), DetalheActivity.class);
        v.getContext().startActivity(intent);
    }
}
