package app.demo.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.demo.R;
import app.demo.Reading;
import app.demo.model.Chapter;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder> {
    public ReadingAdapter(List<Chapter> listChapter) {
        this.listChapter = listChapter;
    }

    List<Chapter> listChapter;
    @NonNull
    @Override
    public ReadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reading, parent, false);
        return new ReadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingViewHolder holder, int position) {

        Chapter chapter = listChapter.get(position);

        Glide.with(holder.itemView.getContext()).load(chapter.getBook().getCoverImg()).into(holder.imgCover);
        holder.imgCover.setImageZoom(1.2f);
        holder.imgCover.setImageAlpha(150);

        holder.tvNameChapter.setText(chapter.getChapterName());
        holder.content.setText(chapter.getContent());
    }

    @Override
    public int getItemCount() {
        return listChapter.size();
    }

    public class ReadingViewHolder extends RecyclerView.ViewHolder {
        ImageFilterView imgCover;
        TextView tvNameChapter, content;
        public ReadingViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCover = itemView.findViewById(R.id.img_cover);
            tvNameChapter = itemView.findViewById(R.id.tv_name_chapter);
            content = itemView.findViewById(R.id.content);
        }
    }
}
