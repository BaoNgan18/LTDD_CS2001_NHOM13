package app.demo.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import app.demo.model.Book;
import app.demo.model.Chapter;
import app.demo.model.Genre;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().create();
    APIService API_SERVICE = new Retrofit.Builder()
            .baseUrl("http://192.168.0.9:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);

    @GET("truyen")
    Call<List<Book>> getListBook();

    @GET("theloai/{genreId}/truyen/")
    Call<List<Book>> getBookByGenre(@Path("genreId") long genreId);

    @GET("theloai")
    Call<List<Genre>> getListGenre();

    @GET("truyen/{bookID}/chuong/")
    Call<List<Chapter>> getAllChaptersByBook(@Path("bookID") long bookID);

    @GET("nguoidung")
    Call<List<User>> getAllUser();

    @GET("nguoidung/{id}/truyenyeuthich/")
    Call<List<Book>> getListFavoriteBookByUser(@Path("userID") long userID);
    @GET("nguoidung/{id}/truyen/")
    Call<List<Book>> getBookByUser(@Path("userID") long userID);

    @POST("nguoidung/{nguoidung_id}/yeuthich/{truyen_id}")
    Call<Book> addBookInFavorites(@Path("userID") long userID, @Path("bookID") long bookID);

    @POST()
    Call<User> createUser(User user);

    @GET("nguoidung/find-by-email/{email}")
    Call<User> findUserByEmail(@Path("email") String email);
}
