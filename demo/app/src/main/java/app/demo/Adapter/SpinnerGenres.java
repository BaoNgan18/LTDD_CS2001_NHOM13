package app.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.demo.PostBook;
import app.demo.R;
import app.demo.model.Chapter;
import app.demo.model.Genre;

public class SpinnerGenres extends ArrayAdapter<Genre> {

    public SpinnerGenres(@NonNull Context context, int resource, @NonNull List<Genre> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_genre, parent, false);
        TextView genreName = convertView.findViewById(R.id.tv_selected);
        List<Genre> genres = new ArrayList<>();
        Genre genre = this.getItem(position);
        if(genre != null)
        {
            genreName.setText(genre.getNameOfGenre());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_genre, parent, false);
        TextView genreName = convertView.findViewById(R.id.tv_selected);

        Genre genre = this.getItem(position);
        if(genre != null)
        {
            genreName.setText(genre.getNameOfGenre());
        }
        return convertView;
    }
}
