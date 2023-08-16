package app.demo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.demo.R;
import app.demo.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.TruyenViewHolder> {
    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    List<Book> bookList;
    @NonNull
    @Override
    public TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new TruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenViewHolder holder, int position) {
        holder.idBook.setText(bookList.get(position).getId()+"");
        holder.nameBook.setText(bookList.get(position).getNameBook());
        holder.nameOfAuthor.setText(bookList.get(position).getUser().getUserName());

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

    public static class TruyenViewHolder extends  RecyclerView.ViewHolder{
        TextView idBook, nameBook, nameOfAuthor, listGenre;



        public TruyenViewHolder(@NonNull View itemView) {
            super(itemView);
//            idBook = itemView.findViewById(R.id.tv_id);
            nameBook = itemView.findViewById(R.id.tv_nameBook);
            listGenre = itemView.findViewById(R.id.tv_genre);
            nameOfAuthor = itemView.findViewById(R.id.tv_nameOfAuthor);


        }

    }
}
