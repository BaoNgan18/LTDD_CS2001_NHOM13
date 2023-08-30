package app.demo.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.Adapter.GenreAdapter;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView rcvBook;
    List<Book> listBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvBook = view.findViewById(R.id.rcv_book);
        rcvBook.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        listBook = new ArrayList<>();
        getListBook();
//        CallAPI(13);
        return view;
    }

    private void getListBook() {
        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    listBook.addAll(response.body());
                    BookAdapter bookAdapter = new BookAdapter(listBook);
                    rcvBook.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

//        public void CallAPI() {
//        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
//            @Override
//            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                listBook.addAll(response.body());
//                BookAdapter bookAdapter = new BookAdapter(listBook);
//                rcvBook.setAdapter(bookAdapter);
////                Toast.makeText(getView().getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Book>> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//                Toast.makeText(getView().getContext(), "That bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public void CallAPI(long genreID) {
//        APIService.API_SERVICE.getBookByGenre(genreID).enqueue(new Callback<List<Book>>() {
//            @Override
//            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                listBook.addAll(response.body());
//                BookAdapter bookAdapter = new BookAdapter(listBook);
//                rcvBook.setAdapter(bookAdapter);
//            }
//            @Override
//            public void onFailure(Call<List<Book>> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//                Toast.makeText(getView().getContext(), "That bai",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}