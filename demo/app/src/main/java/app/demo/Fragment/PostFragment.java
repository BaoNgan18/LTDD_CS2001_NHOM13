package app.demo.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    FloatingActionButton btnAdd;
    private Toolbar mToolBar;
    RecyclerView rcvPost;
    List<Book> books;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "userJson null");
        }
        else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

//        String path;

        rcvPost = view.findViewById(R.id.rcv_post);
        btnAdd = view.findViewById(R.id.add_new);

        books = new ArrayList<>();
        getBookByUser(user.getId());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction().replace(R.id.frm_post, new PostBook()).commit();
            }
        });


//        imgCoverBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postCoverImg();
//            }
//        });

        return view;
    }

    private void getBookByUser(long id) {
        APIService.API_SERVICE.getBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    books.addAll(response.body());
                    BookAdapter bookAdapter = new BookAdapter(books);
                    rcvPost.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    rcvPost.setAdapter(bookAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}