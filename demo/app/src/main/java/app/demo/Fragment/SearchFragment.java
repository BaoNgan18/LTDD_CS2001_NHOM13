package app.demo.Fragment;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.Adapter.SpinnerGenres;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class SearchFragment extends Fragment {

    List<Book> listBook, mBook;
    RecyclerView rcvResult;
    SearchView searchView;
    Toolbar mToolBar;
    BookAdapter bookAdapter;
    Spinner spSearch;
    List<Genre> listGenre, genres;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        rcvResult = view.findViewById(R.id.rcv_search);
        searchView = view.findViewById(R.id.search);
        spSearch = view.findViewById(R.id.sp_genre);


        rcvResult.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        listBook = new ArrayList<>();
        listGenre = new ArrayList<>();
        mBook = new ArrayList<>();

        getAllGenre();
        getAllBook();


        bookAdapter = new BookAdapter(listBook);
        rcvResult.setAdapter(bookAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });

        SpinnerGenres adapter = new SpinnerGenres(SearchFragment.this.getContext(), R.layout.spinner_genre, listGenre);
        spSearch.setAdapter(adapter);
        spSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Genre g = (Genre) spSearch.getItemAtPosition(i);
                getBookByGenre(g.getId());
                BookAdapter b = new BookAdapter(mBook);
                rcvResult.setAdapter(b);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private void getAllGenre() {
        APIService.API_SERVICE.getAllGenre().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if(response.isSuccessful()){
                    listGenre.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }

    private void getBookByGenre(Long id) {
        APIService.API_SERVICE.getBookByGenre(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    mBook.addAll(response.body());
                }
                Log.d("Error", String.valueOf(mBook.size()));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void getAllBook() {
        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    listBook.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}