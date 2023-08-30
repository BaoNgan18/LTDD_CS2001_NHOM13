package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
//import app.demo.Adapter.ChapterAdapter;
import app.demo.Adapter.ChapterAdapter;
import app.demo.model.Book;
import app.demo.model.Chapter;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetail extends AppCompatActivity {

//    Context context;
    private ImageView imgCover;
    private ImageFilterView imgBigCover;
    private TextView tvBookName, tvGenre, tvAuthor, tvDescribe;
    private ImageFilterButton fab;
    private RecyclerView rcvBook, rcvChapter;
    List<Chapter> listChapter;
    List<Book> listBook, listFavBook;
    User user;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "userJson null");
        }
        else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        book = (Book) getIntent().getSerializableExtra("book");

        //ánh xạ các item
        imgCover =findViewById(R.id.img_cover);
        imgBigCover = findViewById(R.id.img_big_cover);
        tvBookName = findViewById(R.id.tv_name);
        tvGenre = findViewById(R.id.tv_list_genre);
        tvAuthor = findViewById(R.id.tv_author);
        tvDescribe = findViewById(R.id.tv_describe);
        rcvBook = findViewById(R.id.rcv_book);

        StringBuilder sb = new StringBuilder();
        book.getListGenre().forEach(t -> sb.append(t.getNameOfGenre()+", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
//        book.setId(id);
        tvBookName.setText(book.getNameBook());
        tvGenre.setText("Thể loại: "+sb.toString());
        tvAuthor.setText("Tác giả: "+book.getUser().getUserName());
        tvDescribe.setText("Không có mô tả");
        Glide.with(getApplicationContext()).load(book.getCoverImg()).into(imgCover);
        Glide.with(getApplicationContext()).load(book.getCoverImg()).into(imgBigCover);

        listFavBook = new ArrayList<>();
        getListFavoriteBookByUser(user.getId());

        fab = findViewById(R.id.fab_add);
        fab.setImageResource(R.drawable.ic_favorite_border);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!fab.isSelected()){
                    addBookInFavorites(user.getId(), book.getId());
                    fab.setImageResource(R.drawable.ic_favorite);
                    fab.setSelected(true);
                    Toast.makeText(getApplicationContext(), "Đã thêm truyện vào danh sách", Toast.LENGTH_LONG).show();
                } else {
                    removeBookFromFavorites(user.getId(), book.getId());
                    fab.setImageResource(R.drawable.ic_favorite_border);
                    fab.setSelected(false);
                    Toast.makeText(getApplicationContext(), "Đã xóa truyện khỏi danh sách", Toast.LENGTH_LONG).show();
                }

            }
        });
        rcvBook = findViewById(R.id.rcv_book);
        LinearLayoutManager linear = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvBook.setLayoutManager(linear);
        listBook = new ArrayList<>();
        getListBook();

        rcvChapter = findViewById(R.id.rcv_chapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcvChapter.setLayoutManager(linearLayout);
        listChapter = new ArrayList<>();
        getBookByID(book.getId());


    }



    private void getListFavoriteBookByUser(long id) {
        APIService.API_SERVICE.getListFavoriteBookByUser(id).enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                boolean isFav = false;
                if(response.isSuccessful())
                {
                    listFavBook = new ArrayList<>();
                    listFavBook.addAll(response.body());
                    for(Book b:listFavBook){
                        if(b.getId().equals(book.getId())){
                            fab.setImageResource(R.drawable.ic_favorite);
                            fab.setSelected(true);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", "Failure ");
            }
        });
    }


    private void removeBookFromFavorites(long id, Long id1) {
        APIService.API_SERVICE.removeBookFromFavorites(id, id1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Log.d("Error", "Delete Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void addBookInFavorites(long id, Long id1) {
        APIService.API_SERVICE.addBookInFavorites(id, id1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Log.d("Error", "Add Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getListBook() {
        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                listBook.addAll(response.body());
                BookAdapter bookAdapter = new BookAdapter(listBook);
                rcvBook.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(BookDetail.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBookByID(long id){
        APIService.API_SERVICE.getBookByID(id).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book book = response.body();
                ChapterAdapter chapterAdapter = new ChapterAdapter(book);
                rcvChapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(BookDetail.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}