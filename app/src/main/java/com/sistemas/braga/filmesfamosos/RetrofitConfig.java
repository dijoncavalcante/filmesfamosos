package com.sistemas.braga.filmesfamosos;

import interfaces.MovieService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public MovieService getMovieService(){
        return this.retrofit.create(MovieService.class);
    }
}
