package app.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import app.demo.API.APIService;
import app.demo.BookDetail;
import app.demo.PostChapter;
import app.demo.R;
import app.demo.Reading;
import app.demo.UpdateChapter;
import app.demo.model.Book;
import app.demo.model.Chapter;
import app.demo.model.Genre;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

//    private List<Chapter> listChapter;
//    public ChapterAdapter(List<Chapter> listChapter){
//        this.listChapter = listChapter;
//    }
    private Book book;

    public ChapterAdapter(Book book) {
        this.book = book;
    }


    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {

        User user = new User();
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Toast.makeText(holder.itemView.getContext(), "không nhận được người dùng", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        Chapter chapter = book.getListChapter().get(position);

        holder.tvNameChapter.setText(chapter.getChapterName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Reading.class);
                Log.d("Chapter", chapter.toString());
                intent.putExtra("book", book);
                intent.putExtra("chapter", chapter);
                intent.putExtra("position", position);
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
                    add.setVisibility(View.GONE);

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), UpdateChapter.class);
                            intent.putExtra("Update", "Update");
                            intent.putExtra("book", book);
                            intent.putExtra("Chapter", chapter);
                            view.getContext().startActivity(intent);
                            dialog.dismiss();
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteChapter(book.getId(), book.getUser().getId());
                            dialog.dismiss();
                        }
                    });

                    return true;
                }
            });
        }
    }

    private void deleteChapter(Long id, long id1) {
        APIService.API_SERVICE.deleteChapter(id, id1).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Log.d("Error", "Delete chapter success");
                }
                else {
                    Log.d("Error", "Delete chapter failure");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return book.getListChapter().size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNameChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameChapter = itemView.findViewById(R.id.tv_name_chapter);
        }


    }
}
