package app.demo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostBook extends Fragment {

    String strNameBook, strDescribe;
    ImageView imgCoverBook;
    EditText edtNameBook, edtDescribe;
    User user;
    Book book;
    Button btnSubmit;
    List<Book> books;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_post_book, container, false);
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "userJson null");
        }
        else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        strNameBook = edtNameBook.getText().toString();
        strDescribe = edtDescribe.getText().toString();
//        imgCoverBook = view.findViewById(R.id.img_post_cover);
        book = new Book(strNameBook, user, strDescribe);
        postBook(book, user.getId());

        btnSubmit = view.findViewById(R.id.btn_submit);
        edtNameBook = view.findViewById(R.id.edt_name_book);
        edtDescribe = view.findViewById(R.id.edt_describe);
        return view;
    }
    private void postBook(Book book, long id) {
        APIService.API_SERVICE.postBook(book, id).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NewBook", book);
                    PostChapter postChapter = new PostChapter();
                    postChapter.setArguments(bundle);
                    getChildFragmentManager().beginTransaction().replace(R.id.frm_postBook, new PostChapter()).addToBackStack(null).commit();
                    Log.d("Error", "post success");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}