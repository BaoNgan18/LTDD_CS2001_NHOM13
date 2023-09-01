package app.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.demo.API.APIService;
import app.demo.BookDetail;
import app.demo.PostBook;
import app.demo.PostChapter;
import app.demo.R;
import app.demo.UpdateBook;
import app.demo.UpdateChapter;
import app.demo.model.Book;
import app.demo.model.Genre;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {
    public BookAdapter(List<Book> bookList) {
        this.listBook = bookList;
        tempList = bookList;
    }

    List<Genre> genres = new ArrayList<>();
    List<Book> listBook, tempList;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        User user = new User();
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Toast.makeText(holder.itemView.getContext(), "không nhận được người dùng", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        Book book = listBook.get(position);

        holder.nameBook.setText(book.getNameBook());
        holder.userName.setText(book.getUser().getUserName());

        String coverImgUrl = book.getCoverImg();
        Glide.with(holder.itemView.getContext())
                .load(coverImgUrl)
                .into(holder.coverImg);

        StringBuilder sb = new StringBuilder();
        book.getListGenre().forEach(t -> sb.append(t.getNameOfGenre()+", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        holder.listGenre.setText(sb.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetail.class);
                intent.putExtra("book", book);
                view.getContext().startActivity(intent);
            }
        });

        if(user.getId() == book.getUser().getId()){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(R.layout.dialog);
                    AlertDialog dialog = builder.show();


                    TextView update = dialog.findViewById(R.id.update);
                    TextView delete = dialog.findViewById(R.id.delete);
                    TextView add = dialog.findViewById(R.id.add);
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(), UpdateBook.class);
                                intent.putExtra("book", book);
                                view.getContext().startActivity(intent);
                                dialog.dismiss();
                            }
                        });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("Error", book.toString());
                            deleteBook(book.getId());
                            dialog.dismiss();
                        }
                    });
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), PostChapter.class);
                            intent.putExtra("book", book);
                            view.getContext().startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    return true;
                }
            });
        }

    }

    private void deleteBook(Long id) {
        APIService.API_SERVICE.deleteBook(id).enqueue(new Callback<Map<String, Boolean>>() {
            @Override
            public void onResponse(Call<Map<String, Boolean>> call, Response<Map<String, Boolean>> response) {
                if(response.isSuccessful()){
                    Log.d("Error", "Delete book success");
                }
                else {
                    Log.d("Error", "Delete book failure");
                }
            }

            @Override
            public void onFailure(Call<Map<String, Boolean>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listBook != null)
            return listBook.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    listBook = tempList;
                } else {
                    List<Book> list = new ArrayList<>();
                    for(Book book: tempList){
                        if(book.getNameBook().toLowerCase().contains(strSearch.toLowerCase())
                            || book.getUser().getUserName().toLowerCase().contains(strSearch.toLowerCase())
                            || genres.contains(strSearch.trim()))
                            list.add(book);

                    }
                    listBook = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listBook;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listBook = (List<Book>) filterResults.values;
                notifyDataSetChanged();
            }


        };
    }

    public static class BookViewHolder extends  RecyclerView.ViewHolder{
        TextView nameBook, userName, listGenre;ImageView coverImg;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
//            idBook = itemView.findViewById(R.id.tv_id);
            nameBook = itemView.findViewById(R.id.tv_nameBook);
            listGenre = itemView.findViewById(R.id.tv_genre);
            userName = itemView.findViewById(R.id.tv_nameOfAuthor);
            coverImg = itemView.findViewById(R.id.iv_coverImg);
        }
    }

    public void getListGenre(){
        APIService.API_SERVICE.getListGenre().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if(response.isSuccessful()){
                    genres.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
