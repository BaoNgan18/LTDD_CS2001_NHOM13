package app.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import app.demo.model.Chapter;

public class Reading extends AppCompatActivity {

    TextView tvNameChapter, tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Chapter chapter = (Chapter) getIntent().getSerializableExtra("chapter");

        tvNameChapter = findViewById(R.id.tv_name_chapter);
        tvContent = findViewById(R.id.content);

        tvNameChapter.setText(chapter.getChapterName());
        tvContent.setText(chapter.getContent());
    }
}