package interfaces;

import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("movie/popular?api_key=11b66e345f4ba9e5676219584613b49a")
    Call<RequisicaoObj> buscarFilmesPopulares();

    @GET("movie/top_rated?api_key=11b66e345f4ba9e5676219584613b49a")
    Call<RequisicaoObj> buscarFilmesMaisVotados();

}

