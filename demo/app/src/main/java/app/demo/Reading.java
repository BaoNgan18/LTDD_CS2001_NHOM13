package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import app.demo.model.Chapter;

public class Reading extends AppCompatActivity {

    TextView tvNameChapter, tvContent;
    ImageFilterView imgCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Chapter chapter = (Chapter) getIntent().getSerializableExtra("chapter");

        tvNameChapter = findViewById(R.id.tv_name_chapter);
        tvContent = findViewById(R.id.content);
        imgCover = findViewById(R.id.img_cover);

        tvNameChapter.setText(chapter.getChapterName());
        tvContent.setText(chapter.getContent());
        Glide.with(getApplicationContext()).load(chapter.getBook().getCoverImg()).into(imgCover);
        imgCover.setImageZoom(1.2f);
        imgCover.setImageAlpha(150);
    }
}