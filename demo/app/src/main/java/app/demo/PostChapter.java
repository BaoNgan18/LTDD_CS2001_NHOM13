package app.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.BookAdapter;
import app.demo.Adapter.ChapterAdapter;
import app.demo.model.Book;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostChapter extends AppCompatActivity {

    TextView tv;
    Book book;
    RecyclerView rcvChapter;
    FloatingActionButton add;
    List<Chapter> listChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_chapter);

        book = (Book) getIntent().getSerializableExtra("book");

        rcvChapter = findViewById(R.id.rcv_chapter_post);
        listChapter = new ArrayList<>();
        getAllChaptersByBook(book.getId());

        tv = findViewById(R.id.textView);
        add = findViewById(R.id.add_new);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostChapter.this, CreateChapter.class);
                intent.putExtra("Book", book);
                startActivity(intent);
            }
        });

    }

    private void getAllChaptersByBook(Long id) {
        APIService.API_SERVICE.getAllChaptersByBook(id).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                if(response.isSuccessful())
                {
                    listChapter.addAll(response.body());
                }
                if(listChapter.isEmpty()){
                    rcvChapter.setVisibility(View.GONE);
                }
                else{
                    tv.setText("Các chương đã đăng");
                    ChapterAdapter chapterAdapter = new ChapterAdapter(book);
                    rcvChapter.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    rcvChapter.setAdapter(chapterAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}