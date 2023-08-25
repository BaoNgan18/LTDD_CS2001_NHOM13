package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
//import app.demo.Adapter.ChapterAdapter;
import app.demo.Adapter.ChapterAdapter;
import app.demo.model.Book;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetail extends AppCompatActivity {

//    Context context;
    private ImageView imgCover;
    private ImageFilterView imgBigCover;
    private TextView tvBookName, tvGenre, tvAuthor, tvDescribe;
    private FloatingActionButton fab;
    private RecyclerView rcvBook, rcvChapter;
    private BookAdapter bookAdapter;
    private ImageButton btnShare, btnFav;
    List<Chapter> listChapter;
    List<Book> listBook;

//    public BookDetail(Context context){
//        this.context = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Book book = (Book) getIntent().getSerializableExtra("book");

        //ánh xạ các item
        imgCover =findViewById(R.id.img_cover);
        imgBigCover = findViewById(R.id.img_big_cover);
        tvBookName = findViewById(R.id.tv_name);
        tvGenre = findViewById(R.id.tv_list_genre);
        tvAuthor = findViewById(R.id.tv_author);
        tvDescribe = findViewById(R.id.tv_describe);
        rcvBook = findViewById(R.id.rcv_book);
        btnFav = findViewById(R.id.btn_fav);
        btnShare = findViewById(R.id.btn_share);

        StringBuilder sb = new StringBuilder();
        book.getListGenre().forEach(t -> sb.append(t.getNameOfGenre()+", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
//        book.setId(id);
        tvBookName.setText(book.getNameBook());
        tvGenre.setText(sb.toString());
        tvAuthor.setText(book.getUser().getUserName());
        tvDescribe.setText("Không có mô tả");
        Glide.with(getApplicationContext()).load(book.getCoverImg()).into(imgCover);
        Glide.with(getApplicationContext()).load(book.getCoverImg()).into(imgBigCover);


        btnFav = findViewById(R.id.btn_fav);
        btnFav.setBackground(null);
        btnShare = findViewById(R.id.btn_share);
        btnShare.setBackground(null);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!btnFav.isSelected()){
                    btnFav.setSelected(true);
                    Toast.makeText(getApplicationContext(), "Đã thêm truyện vào danh sách yêu thích", Toast.LENGTH_LONG).show();
                    btnFav.setImageResource(R.drawable.ic_favorite);
                } else{
                    btnFav.setSelected(false);
                    Toast.makeText(getApplicationContext(), "Đã xóa truyện khỏi danh sách yêu thích", Toast.LENGTH_LONG).show();
                    btnFav.setImageResource(R.drawable.ic_favorite_border);
                }

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đã chia sẻ truyện", Toast.LENGTH_LONG).show();
            }
        });

        fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(view.getContext(), LibraryFragment.class);
//
//                Bundle extras = new Bundle();
//                extras.putInt("ID", book.getResourceID());
//                extras.putString("Title", book.getTitle());
//                intent.putExtras(extras);
//
//                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Đã thêm truyện vào danh sách", Toast.LENGTH_LONG).show();
            }
        });
//        rcvBook = findViewById(R.id.rcv_book);
//        LinearLayoutManager linear = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        rcvBook.setLayoutManager(linear);
//        listBook = new ArrayList<>();
//        CallAPI();

        rcvChapter = findViewById(R.id.rcv_chapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcvChapter.setLayoutManager(linearLayout);
        listChapter = new ArrayList<>();
        CallAPI(book.getId());


    }

    public void CallAPI(long bookID) {
        APIService.API_SERVICE.getAllChaptersByBook(bookID).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {

                listChapter.addAll(response.body());
                ChapterAdapter chapterAdapter = new ChapterAdapter(listChapter);
                rcvChapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(BookDetail.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public void CallAPI() {
//        APIService.API_SERVICE.getListBook().enqueue(new Callback<List<Book>>() {
//            @Override
//            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                listBook.addAll(response.body());
//                BookAdapter bookAdapter = new BookAdapter(listBook);
//                rcvBook.setAdapter(bookAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Book>> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//                Toast.makeText(BookDetail.this, "That bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}