package com.sistemas.braga.filmesfamosos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by dijon on 07/07/2018.
 */

public class MainGridView extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(MainGridView.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DetalheActivity.class);
                intent.putExtra("posicao", position);
                startActivity(intent);
            }
        });

        URL urlFilmes = TheMovieDB.builtUrl(1);

    }
}
