package app.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import app.demo.API.APIService;
import app.demo.model.Book;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateChapter extends AppCompatActivity {

    AlertDialog dialog;

    TextView tvCancel, tvPost;
    EditText edtNameChapter, edtContent;
    String strNameChapter, strContent, msg;
    Chapter chapter, newChapter;
    Book book;
    Boolean update = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_chapter);


        edtNameChapter = findViewById(R.id.edt_name_chapter);
        edtContent = findViewById(R.id.edt_content_chapter);
        tvCancel = findViewById(R.id.tv_cancel);
        tvPost = findViewById(R.id.tv_post);

        book = (Book) getIntent().getSerializableExtra("Book");
        Log.d("Error", book.toString());

        tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strNameChapter = edtNameChapter.getText().toString();
                strContent = edtContent.getText().toString();
                newChapter = new Chapter(book, strNameChapter, strContent);
                createChapter(book.getId(), newChapter);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });

    }

    private void createChapter(Long id, Chapter newChapter) {
        APIService.API_SERVICE.createChapter(id, newChapter).enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if (response.isSuccessful()) {
                    Log.d("Error", "Post chapter success");
                    onBackPressed();
                } else {
                    Log.d("Error", "Post chapter failure");
                }
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void Alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn thoát?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.show();
    }
}