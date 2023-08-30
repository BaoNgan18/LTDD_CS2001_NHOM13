package app.demo.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import app.demo.API.APIService;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingFragment extends Fragment {
    ImageFilterButton btnNextStep;
    String strNameBook, strDescribe;
    ImageView imgCoverBook;
    User user;
    Book book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_writing, container, false);

        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "userJson null");
        }
        else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }
        String path;

        btnNextStep = view.findViewById(R.id.btn_next_step);
        strNameBook = view.findViewById(R.id.edt_name_book).toString();
        strDescribe = view.findViewById(R.id.edt_describe).toString();
        imgCoverBook = view.findViewById(R.id.img_post_cover);

        postBook(new Book(strNameBook, user, strDescribe), user.getId());

//        imgCoverBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postCoverImg();
//            }
//        });

        return view;
    }

    private void postBook(Book book, long id) {
        APIService.API_SERVICE.postBook(book, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    Log.d("Error", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}