package com.sistemas.braga.filmesfamosos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import model.Result;

public class DetalheActivity extends AppCompatActivity {

    ImageView ivDetalhe;
    TextView tvDescricaoTitulo;
    TextView tvVotosValor;
    TextView tvLancamentoValor;
    TextView tvDescricaoSinopse;
    TextView tvMediaDeEstrelasValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivDetalhe = findViewById(R.id.ivDetalhe);
        tvDescricaoTitulo = findViewById(R.id.tvDescricaoTitulo);
        tvVotosValor = findViewById(R.id.tvVotosValor);
        tvMediaDeEstrelasValor = findViewById(R.id.tvMediaDeEstrelasValor);
        tvLancamentoValor = findViewById(R.id.tvLancamentoValor);
        tvDescricaoSinopse= findViewById(R.id.tvDescricaoSinopse);


        Result  result = (Result) getIntent().getSerializableExtra("filme");

        if(result != null) {
            Picasso.with(getBaseContext())
                    .load(result.getPoster_path())
                    .placeholder(R.drawable.ic_menu_camera)
                    .error(R.drawable.ic_menu_camera)
                    .into(ivDetalhe);
            tvDescricaoTitulo.setText(result.getTitle());
            tvVotosValor.setText(String.valueOf(result.getVote_count()));
            tvMediaDeEstrelasValor.setText(String.valueOf(result.getVote_average()));
            tvLancamentoValor.setText(result.getRelease_date());
            tvDescricaoSinopse.setText(result.getOverview());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
