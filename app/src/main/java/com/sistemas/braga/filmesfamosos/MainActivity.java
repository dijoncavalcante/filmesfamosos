package com.sistemas.braga.filmesfamosos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    RequisicaoObj requisicaoObj;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lLayout = new GridLayoutManager(MainActivity.this, 2);
        final RecyclerView rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        //codigo usando o retrofit
        Call<RequisicaoObj> call = new RetrofitConfig().getMovieService().buscarFilmesPopulares();

        call.enqueue(new Callback<RequisicaoObj>() {
            @Override
            public void onResponse(Call<RequisicaoObj> call, Response<RequisicaoObj> response) {
                //pegar a resposta
                requisicaoObj = response.body();
                //definir aqui os valores
                RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, requisicaoObj.getResults());
                rView.setAdapter(rcAdapter);
            }

            @Override
            public void onFailure(Call<RequisicaoObj> call, Throwable t) {
                //tratar algum erro
                Log.e(TAG, "Erro ao buscar os filmes");
                Snackbar.make(rView, "Erro ao buscar os filmes", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gridiew, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelecionado = item.getItemId();
        if(menuItemSelecionado == R.id.action_popular){
            Toast.makeText(getBaseContext(),"action_popular",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(menuItemSelecionado == R.id.action_top_rated){
            Toast.makeText(getBaseContext(),"action_top_rated",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
