package app.demo.Adapter;

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

    private List<Chapter> chapters;
    public ChapterAdapter(List<Chapter> listChapter){
        chapters = listChapter;
    }
    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {

        Chapter chapter = chapters.get(position);

        holder.tvNameChapter.setText(chapter.getChapterName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Reading.class);
                Log.d("Book", chapter.toString());
                intent.putExtra("chapter", chapter);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameChapter = itemView.findViewById(R.id.tv_name_chapter);
        }
    }
}
