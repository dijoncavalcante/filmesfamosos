package com.sistemas.braga.filmesfamosos;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by dijon on 09/07/2018.
 */

public class TheMovieDB {
    public static final String API_KEY = "api_key=teste";
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String TAMANHO = "3";
    public static final String POPULAR = "/movie/popular?";//sortBy==1
    public static final String TOP_RATED = "/movie/top_rated?";//sortBy==2


    public static URL builtUrl(int sortBy) {
        Uri builtUri= null;
        if (sortBy == 1) {
            builtUri = Uri.parse(BASE_URL + TAMANHO + POPULAR + API_KEY).buildUpon().build();
        } else {
             builtUri = Uri.parse(BASE_URL + TAMANHO + TOP_RATED + API_KEY).buildUpon().build();
        }

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
