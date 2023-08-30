package app.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import app.demo.API.APIService;
import app.demo.Adapter.ChapterAdapter;
import app.demo.Adapter.ReadingAdapter;
import app.demo.Adapter.SpinnerAdapter;
import app.demo.model.Book;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reading extends AppCompatActivity {

    TextView tvNameChapter, tvContent;
    ImageView imgCover;
    RecyclerView rcvReading;
    List<Chapter> listChapter;
    NestedScrollView nsvReading;
    ImageFilterButton btnPrevious, btnNext, btnHome;
    Chapter chapter;
    Spinner spChapter;
    Book book;
    MaterialToolbar navBar;
    int curentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);


        chapter = (Chapter) getIntent().getSerializableExtra("chapter");
        book = (Book) getIntent().getSerializableExtra("book");


        tvNameChapter = findViewById(R.id.tv_name_chapter);
        tvContent = findViewById(R.id.content);
        imgCover = findViewById(R.id.img_cover);
        nsvReading = findViewById(R.id.nsv_reading);
        btnNext = findViewById(R.id.btn_next);
        btnPrevious = findViewById(R.id.btn_previous);
        btnHome = findViewById(R.id.btn_home);
        spChapter = findViewById(R.id.sp_chapter);
        navBar = findViewById(R.id.nav_bar);
        navBar.setVisibility(View.GONE);

        disPlayChapter(chapter);

        listChapter = new ArrayList<>();
        getAllChaptersByBook(book.getId());

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        nsvReading.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(i1<i3)
                    navBar.setVisibility(View.VISIBLE);
                else if(i1>i3)
                    navBar.setVisibility(View.GONE);

            }
        });

        ListIterator<Chapter> listIterator = listChapter.listIterator();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chapter nextChapter = listIterator.next();
                disPlayChapter(nextChapter);
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chapter previous = listIterator.previous();
                disPlayChapter(previous);
            }
        });


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        rcvReading.setLayoutManager(linearLayoutManager);
//        rcvReading = findViewById(R.id.rcv_reading);
    }

    private void getAllChaptersByBook(long id) {
        APIService.API_SERVICE.getAllChaptersByBook(id).enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                listChapter.addAll(response.body());
                if (listChapter.isEmpty()) {
                    Log.d("Error", "khong co listChapter");
                } else {
                    SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getApplicationContext(), R.layout.spinner, listChapter);
                    spChapter.setAdapter(spinnerAdapter);
                    spChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Chapter chapter1 = (Chapter) spChapter.getItemAtPosition(i);
                            disPlayChapter(chapter1);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                Toast.makeText(Reading.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void disPlayChapter(Chapter chapter){
        tvNameChapter.setText(chapter.getChapterName());
        tvContent.setText(chapter.getContent());
    }
}