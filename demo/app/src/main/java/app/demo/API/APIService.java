package app.demo.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import app.demo.model.TheLoai;
import app.demo.model.Truyen;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    Gson gson = new GsonBuilder().create();
    APIService API_SERVICE = new Retrofit.Builder()
            .baseUrl("http://192.168.1.246:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);

    @GET("truyen")
    Call<List<Truyen>> layDSTruyen();

}
