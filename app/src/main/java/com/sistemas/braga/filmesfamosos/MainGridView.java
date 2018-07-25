package com.sistemas.braga.filmesfamosos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dijon on 07/07/2018.
 */

public class MainGridView extends AppCompatActivity {

public static final String TAG = "MainGridView";

    private GridLayoutManager lLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //onResponse("https://api.themoviedb.org/3/movie/popular?page=1&language=en-US&api_key=11b66e345f4ba9e5676219584613b49a");
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(this));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                //Toast.makeText(MainGridView.this, "" + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getBaseContext(), DetalheActivity.class);
//                intent.putExtra("posicao", position);
//                startActivity(intent);
//            }
//        });
//
//        URL urlFilmes = TheMovieDB.builtUrl(1);

        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(MainGridView.this, 4);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainGridView.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_gridiew, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("United States", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Canada", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("United Kingdom", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Germany", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Sweden", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("United Kingdom", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Germany", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Sweden", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("United States", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Canada", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("United Kingdom", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Germany", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Sweden", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("United Kingdom", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Germany", R.drawable.ic_menu_camera));
        allItems.add(new ItemObject("Sweden", R.drawable.ic_menu_camera));

        return allItems;
    }

    private Gson gson;

    public void onResponse(String response){
        JSONObject jsonObject=null;
        try {
            jsonObject =  new JSONObject(response);

            Filmes filmes = gson.fromJson(jsonObject.getJSONObject("data").toString(), Filmes.class);
            int count = 1;
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
