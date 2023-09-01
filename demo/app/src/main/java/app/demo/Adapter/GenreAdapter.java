package app.demo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genres, tempList;
    List<Book> listBook;

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
        this.tempList = genres;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {

        Genre genre = genres.get(position);

            holder.tvGenreName.setText(genre.getNameOfGenre());
            getListBook(genre.getId());
            BookAdapter bookAdapter = new BookAdapter(listBook);
            holder.rcvBook.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if(genres != null)
            return genres.size();
        return 0;
    }

    public static class GenreViewHolder extends  RecyclerView.ViewHolder{
        TextView tvGenreName;
        RecyclerView rcvBook;
        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenreName = itemView.findViewById(R.id.tv_genre_name);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }

    private void getListBook(long id) {
        APIService.API_SERVICE.getBookByGenre(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                listBook.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


}
