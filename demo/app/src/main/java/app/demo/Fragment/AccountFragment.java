package app.demo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.User;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private CircleImageView imgUserAvatar;
    private TextView tvUserName, tvUserFullName, tvTitleUp, tvTitleFav;
    private RecyclerView rcvFavBook, rcvUploadBook;
    List<Book> listFavBook, listUploadBook;
    User user;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);


//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        imgUserAvatar = view.findViewById(R.id.img_user_avatar);
        tvUserName = view.findViewById(R.id.tv_user_name);
        rcvFavBook = view.findViewById(R.id.rcv_fav_book);
        rcvUploadBook = view.findViewById(R.id.rcv_upload_book);
        tvTitleUp = view.findViewById(R.id.title_up);
        tvTitleFav = view.findViewById(R.id.title_fav);

        tvUserName.setText(user.getUserName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvFavBook.setLayoutManager(linearLayoutManager);
        rcvUploadBook.setLayoutManager(linearLayoutManager);
        listFavBook = new ArrayList<>();
        CallAPI(user.getId());
        listUploadBook = new ArrayList<>();
        Call(user.getId());

        return view;
    }

    public void CallAPI(long id) {
        APIService.API_SERVICE.getListFavoriteBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                listFavBook.addAll(response.body());
                BookAdapter bookAdapter = new BookAdapter(listFavBook);
                rcvFavBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                tvTitleFav.setText("Khong co danh sach doc");
                rcvFavBook.setVisibility(View.VISIBLE);
            }
        });
    }

    public  void Call(long id){
        APIService.API_SERVICE.getBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                listUploadBook.addAll(response.body());
                BookAdapter bookAdapter = new BookAdapter(listUploadBook);
                rcvUploadBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                tvTitleUp.setText("Khong co danh sach doc");
                rcvFavBook.setVisibility(View.VISIBLE);
            }
        });
    }

}