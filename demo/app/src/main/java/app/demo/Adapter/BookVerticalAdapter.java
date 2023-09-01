package app.demo.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.BookDetail;
import app.demo.PostBook;
import app.demo.R;
import app.demo.model.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookVerticalAdapter extends RecyclerView.Adapter<BookVerticalAdapter.BookViewHolder> {
    public BookVerticalAdapter(List<Book> bookList) {
        this.listBook = bookList;
    }

    List<Book> listBook;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_vertical, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        Book book = listBook.get(position);

        holder.nameBook.setText(book.getNameBook());
        String coverImgUrl = book.getCoverImg();
        Glide.with(holder.itemView.getContext())
                .load(coverImgUrl)
                .into(holder.coverImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetail.class);
                intent.putExtra("book", book);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(listBook != null)
            return listBook.size();
        return 0;
    }

    public static class BookViewHolder extends  RecyclerView.ViewHolder{
        TextView nameBook;ImageView coverImg;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
//            idBook = itemView.findViewById(R.id.tv_id);
            nameBook = itemView.findViewById(R.id.tv_name);
            coverImg = itemView.findViewById(R.id.img_cover);
        }
    }
}
