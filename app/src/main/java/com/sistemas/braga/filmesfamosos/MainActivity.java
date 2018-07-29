package com.sistemas.braga.filmesfamosos;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    RequisicaoObj requisicaoObj;
    Call<RequisicaoObj> call;
    private final static String TAG = "MainActivity";
    SwipeRefreshLayout mSwipeRefreshLayout;
    public boolean flagFiltroPrincial = true;//filmes populares

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout =  findViewById(R.id.swipeToRefresh);

        if (savedInstanceState != null) {
            // Restaura valores dos membros a partir do estado salvo
            flagFiltroPrincial = savedInstanceState.getBoolean("flagFiltroPrincial");
        }

        //codigo usando o retrofit
        CarregarFilmesConformeFiltro();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CarregarFilmesConformeFiltro();
                mSwipeRefreshLayout.setRefreshing(false);
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
            buscarFilmesPopulares();
            return true;
        }
        if(menuItemSelecionado == R.id.action_top_rated){
            buscarFilmesMaisVotados();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buscarFilmesPopulares(){
        flagFiltroPrincial = true;
        lLayout = new GridLayoutManager(MainActivity.this, 2);

        final RecyclerView rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rView.setFitsSystemWindows(true);
        call = new RetrofitConfig().getMovieService().buscarFilmesPopulares();
        call.enqueue(new Callback<RequisicaoObj>() {
            @Override
            public void onResponse(Call<RequisicaoObj> call, Response<RequisicaoObj> response) {
                requisicaoObj = response.body();
                RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, requisicaoObj.getResults());
                rView.setAdapter(rcAdapter);
            }

            @Override
            public void onFailure(Call<RequisicaoObj> call, Throwable t) {
                Log.e(TAG, "Sem conexão com a internet");
                Snackbar snackbar = Snackbar
                        .make(rView, "Sem conexão com a internet", Snackbar.LENGTH_SHORT)
                        .setAction("Tentar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                buscarFilmesPopulares();
                            }
                        });
                snackbar.show();
            }
        });
    }
    public void buscarFilmesMaisVotados(){
        flagFiltroPrincial = false;
        lLayout = new GridLayoutManager(MainActivity.this, 2);

        final RecyclerView rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rView.setFitsSystemWindows(true);
        call = new RetrofitConfig().getMovieService().buscarFilmesMaisVotados();
        call.enqueue(new Callback<RequisicaoObj>() {
            @Override
            public void onResponse(Call<RequisicaoObj> call, Response<RequisicaoObj> response) {
                requisicaoObj = response.body();
                RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, requisicaoObj.getResults());
                rView.setAdapter(rcAdapter);
            }

            @Override
            public void onFailure(Call<RequisicaoObj> call, Throwable t) {
                Log.e(TAG, "Sem conexão com a internet");
                Snackbar snackbar = Snackbar
                        .make(rView, "Sem conexão com a internet", Snackbar.LENGTH_SHORT)
                        .setAction("Tentar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CarregarFilmesConformeFiltro();
                            }
                        });
                snackbar.show();
            }
        });
    }

    public void CarregarFilmesConformeFiltro(){
        if(flagFiltroPrincial){
            buscarFilmesPopulares();
        }
        else {
            buscarFilmesMaisVotados();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("flagFiltroPrincial", flagFiltroPrincial);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        flagFiltroPrincial = savedInstanceState.getBoolean("flagFiltroPrincial");
    }
}
