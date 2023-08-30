package app.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import app.demo.R;
import app.demo.model.Chapter;

public class SpinnerAdapter extends ArrayAdapter<Chapter> {
    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Chapter> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner, parent, false);
        TextView tvChapterName = convertView.findViewById(R.id.tv_selected);

        Chapter chapter = this.getItem(position);
        if(chapter!= null)
            tvChapterName.setText(chapter.getChapterName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner, parent, false);
        TextView tvChapterName = convertView.findViewById(R.id.tv_selected);

        Chapter chapter = this.getItem(position);
        if(chapter!= null)
            tvChapterName.setText(chapter.getChapterName());
        return convertView;
    }
}
