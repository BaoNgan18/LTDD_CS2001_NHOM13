package app.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.ReadingAdapter;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reading extends AppCompatActivity {

    TextView tvNameChapter, tvContent;
    ImageView imgCover;
    RecyclerView rcvReading;
    List<Chapter> listChapter;
    ScrollView scrollView;
    Chapter chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);


        chapter = (Chapter) getIntent().getSerializableExtra("chapter");
//        listChapter = new ArrayList<>();
//        getAllChaptersByBook();
//        if(listChapter==null)
//            Toast.makeText(getApplicationContext(), "ListChapter null", Toast.LENGTH_SHORT).show();
//        int position = listChapter.get();

        tvNameChapter = findViewById(R.id.tv_name_chapter);
        tvContent = findViewById(R.id.content);
        imgCover = findViewById(R.id.img_cover);
        scrollView = findViewById(R.id.scrollView);

        tvNameChapter.setText(chapter.getChapterName());
        tvContent.setText(chapter.getContent());
//        Glide.with(getApplicationContext()).load(chapter.getBook().getCoverImg()).into(imgCover);
//        imgCover.setImageZoom(1.2f);
//        imgCover.setImageAlpha(150);


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        rcvReading.setLayoutManager(linearLayoutManager);
//        rcvReading = findViewById(R.id.rcv_reading);
    }

//    private void getAllChaptersByBook() {
//        APIService.API_SERVICE.getAllChaptersByBook(chapter.getBook().getId()).enqueue(new Callback<List<Chapter>>() {
//            @Override
//            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
//                listChapter.addAll(response.body());
//                Log.d("Error", listChapter.toString());
////                ReadingAdapter readingAdapter = new ReadingAdapter(listChapter);
////                rcvReading.setAdapter(readingAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Chapter>> call, Throwable t) {
//                Toast.makeText(Reading.this, "That bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}