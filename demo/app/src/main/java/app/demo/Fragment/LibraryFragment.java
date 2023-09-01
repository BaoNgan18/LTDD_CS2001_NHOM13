package app.demo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.Adapter.BookVerticalAdapter;
import app.demo.R;
import app.demo.model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {

    RecyclerView rcvBook;
    List<Book> listBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
        rcvBook = view.findViewById(R.id.rcv_book);
        rcvBook.setLayoutManager(gridLayoutManager);
        listBook = new ArrayList<>();
        CallAPI();

        return view;
    }

    public void CallAPI() {
        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                listBook.addAll(response.body());
                BookVerticalAdapter bookAdapter = new BookVerticalAdapter(listBook);
                rcvBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}