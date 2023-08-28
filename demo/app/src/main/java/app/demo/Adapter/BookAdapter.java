package app.demo.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.demo.BookDetail;
import app.demo.R;
import app.demo.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {
    public BookAdapter(List<Book> bookList) {
        this.listBook = bookList;
        tempList = bookList;
    }

    List<Book> listBook, tempList;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

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
                Log.d("Book", book.toString());
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
                        if(book.getNameBook().toLowerCase().contains(strSearch.toLowerCase()))
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
}
