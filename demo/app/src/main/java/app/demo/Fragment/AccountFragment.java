package app.demo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.LoginActivity;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.User;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private CircleImageView imgUserAvatar;
    private TextView tvUserName, tvTitleUp, tvTitleFav;
    private RecyclerView rcvFavBook, rcvUploadBook;
    List<Book> listFavBook, listUploadBook;
    ImageButton btnLogout;
    User user;
    Toolbar mToolBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Toast.makeText(getView().getContext(), "không nhận được người dùng", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        imgUserAvatar = view.findViewById(R.id.img_user_avatar);
        tvUserName = view.findViewById(R.id.tv_user_name);
        rcvFavBook = view.findViewById(R.id.rcv_fav_book);
        rcvUploadBook = view.findViewById(R.id.rcv_upload_book);
        tvTitleUp = view.findViewById(R.id.tv_title_up);
        tvTitleFav = view.findViewById(R.id.tv_title_fav);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnLogout.setBackground(null);

        tvUserName.setText(user.getUserName());
        imgUserAvatar.setImageResource(R.drawable.r);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvFavBook.setLayoutManager(linearLayoutManager);
        rcvUploadBook.setLayoutManager(linearLayout);
        listFavBook = new ArrayList<>();
        getListFavBook(user.getId());
        listUploadBook = new ArrayList<>();
        getBook(user.getId());
        rcvFavBook.setVisibility(View.GONE);
        rcvUploadBook.setVisibility(View.GONE);
        tvTitleUp.setVisibility(View.GONE);
        tvTitleFav.setVisibility(View.GONE);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("user");
                editor.remove("isLogged");
                editor.apply();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getListFavBook(long id) {
        APIService.API_SERVICE.getListFavoriteBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    tvTitleFav.setVisibility(View.VISIBLE);
                    rcvFavBook.setVisibility(View.VISIBLE);
                    listFavBook.addAll(response.body());
                    BookAdapter bookAdapter = new BookAdapter(listFavBook);
                    rcvFavBook.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                tvTitleFav.setText("Khong co danh sach doc");
            }
        });
    }

    public void getBook(long id) {
        APIService.API_SERVICE.getBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    tvTitleUp.setVisibility(View.VISIBLE);
                    rcvUploadBook.setVisibility(View.VISIBLE);
                    listUploadBook.addAll(response.body());
                    BookAdapter bookAdapter = new BookAdapter(listUploadBook);
                    rcvUploadBook.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                tvTitleUp.setText("Khong co danh sach doc");
            }
        });
    }

}