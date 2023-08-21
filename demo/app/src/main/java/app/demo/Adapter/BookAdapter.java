package app.demo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.demo.R;
import app.demo.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    List<Book> bookList;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.idBook.setText(bookList.get(position).getId()+"");
        holder.nameBook.setText(bookList.get(position).getNameBook());
        holder.userName.setText(bookList.get(position).getUser().getUserName());

        String coverImgUrl = bookList.get(position).getCoverImg();
        Glide.with(holder.itemView.getContext())
                .load(coverImgUrl)
                .into(holder.coverImg);

        StringBuilder sb = new StringBuilder();
        bookList.get(position).getListGenre().forEach(t -> sb.append(t.getNameOfGenre()+", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        holder.listGenre.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends  RecyclerView.ViewHolder{
        TextView idBook, nameBook, userName, listGenre;
        ImageView coverImg;



        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            idBook = itemView.findViewById(R.id.tv_id);
            nameBook = itemView.findViewById(R.id.tv_nameBook);
            listGenre = itemView.findViewById(R.id.tv_genre);
            userName = itemView.findViewById(R.id.tv_nameOfAuthor);
            coverImg = itemView.findViewById(R.id.iv_coverImg);

        }

    }
}
