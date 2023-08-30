package app.demo.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;

import app.demo.model.Book;
import app.demo.model.Chapter;
import app.demo.model.Genre;
import app.demo.model.User;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().create();
    APIService API_SERVICE = new Retrofit.Builder()
            .baseUrl("http://192.168.1.87:8080/api/")
//            .baseUrl("http://192.168.0.9:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);

    @GET("truyen")
    Call<List<Book>> getListBook();
    @GET("truyen/{id}")
    Call<Book> getBookByID(@Path("id") long id);

    @GET("theloai/{genreId}/truyen/")
    Call<List<Book>> getBookByGenre(@Path("genreId") long genreId);

    @GET("theloai")
    Call<List<Genre>> getListGenre();

    @GET("truyen/{bookID}/chuong/")
    Call<List<Chapter>> getAllChaptersByBook(@Path("bookID") long bookID);

    @GET("nguoidung")
    Call<List<User>> getAllUser();

    @GET("nguoidung/{id}/truyenyeuthich/")
    Call<List<Book>> getListFavoriteBookByUser(@Path("id") long userID);
    @GET("nguoidung/{id}/truyen/")
    Call<List<Book>> getBookByUser(@Path("id") long userID);

    @POST("nguoidung/{userID}/yeuthich/{bookID}")
    Call<Void> addBookInFavorites(@Path("userID") long userID, @Path("bookID") long bookID);

    @POST("nguoidung")
    Call<User> createUser(@Body User user);
    @POST("nguoidung/login")
    Call<User> loginAccount(@Body User user);

    @GET("nguoidung/find-by-email/{email}")
    Call<User> findUserByEmail(@Path("email") String email);

    @DELETE("nguoidung/{userId}/xoayeuthich/{bookId}")
    Call<Void> removeBookFromFavorites(@Path("userId") long userID, @Path("bookId") long bookID);

    @POST("truyen/{id}/dang-anh")
    Call<Void> postCoverImg(@Part MultipartBody.Part filePart, @Path("id") long bookID);

    @POST("truyen/{id}/dang_truyen")
    Call<Void> postBook(@Body Book book, @Path("id") long userID);
}
