package app.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.demo.BookDetail;
import app.demo.R;
import app.demo.Reading;
import app.demo.model.Book;
import app.demo.model.Chapter;

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

        Chapter chapter = book.getListChapter().get(position);

        holder.tvNameChapter.setText(chapter.getChapterName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Reading.class);
                Log.d("Chapter", chapter.toString());
                intent.putExtra("book", book);
                intent.putExtra("chapter", chapter);
                view.getContext().startActivity(intent);
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
