package app.demo;

import static app.demo.R.color.gray;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

import app.demo.API.APIService;
import app.demo.Adapter.GenreAdapter;
import app.demo.Adapter.SpinnerAdapter;
import app.demo.Adapter.SpinnerGenres;
import app.demo.model.Book;
import app.demo.model.Chapter;
import app.demo.model.Genre;
import app.demo.model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import app.demo.Adapter.GenreAdapter;

public class PostBook extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    private final static String KEY_UPDATE = "Update";

    AlertDialog dialog;
    MultipartBody.Part body;
    TextView tvCancel, tvPost;
    String strName, strDescribe, strGenre, msg;
    ImageView imgCoverBook;
    EditText edtNameBook, edtDescribe, edtGenre;
    User user;
    Book book, newBook, oldBook;
    Button btnSubmit;
    List<Genre> genres, listGenre, mGenres;
    Uri mUri;
    Spinner spGenre;
    StringBuilder sb;


    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data == null) {
                    return;
                }
                Uri uri = data.getData();
                mUri = uri;
                Log.d("Uri", mUri.getPath());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgCoverBook.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_book);

        SharedPreferences sharedPreferences = this.getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "userJson null");
        } else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        btnSubmit = findViewById(R.id.btn_submitPost);
        edtNameBook = findViewById(R.id.edt_namePost);
        edtDescribe = findViewById(R.id.edt_describePost);
        spGenre = findViewById(R.id.sp_genre);
        imgCoverBook = findViewById(R.id.img_post_cover);
        tvPost = findViewById(R.id.tv_post);
        tvCancel = findViewById(R.id.tv_cancel);
        edtGenre = findViewById(R.id.edt_genrePost);

        mGenres = new ArrayList<>();
        genres = new ArrayList<>();
        getListGenre();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtNameBook.getText().toString();
                strDescribe = edtDescribe.getText().toString();
                book = new Book(strName, mGenres, strDescribe, user);
                postBook(book, user.getId());
            }
        });

        imgCoverBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }

    private void getListGenre() {
        APIService.API_SERVICE.getAllGenre().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if(response.isSuccessful()){
                    genres.addAll(response.body());

                    SpinnerGenres adapter = new SpinnerGenres(getApplicationContext(), R.layout.spinner_genre, genres);
                    spGenre.setAdapter(adapter);
                    spGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            StringBuilder a = new StringBuilder();
                            Genre g = (Genre) spGenre.getItemAtPosition(i);
                            mGenres.add(g);
                            a.append(g.getNameOfGenre() + " ");
                            edtGenre.setText(a.toString());
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else
                    Log.d("Error", "load list genre failure");
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }


    private void updateBook(Long id, Book newBook) {
        APIService.API_SERVICE.updateBook(id, newBook).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Log.d("Error", "Update book success");
                } else {
                    Log.d("Error", "Update book failure");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void postBook(Book book, long id) {
        APIService.API_SERVICE.postBook(book, id).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Log.d("Error", "Post book Success");
                    Book b = response.body();

                    String url = RealPathUtil.getRealPath(PostBook.this, mUri);
                    Log.d("Error", url);
                    File file = new File(url);

                    Log.d("File", file.toString());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    postCoverImg(body, b.getId());

                    Intent intent = new Intent(PostBook.this, PostChapter.class);
                    intent.putExtra("book", b);
                    startActivity(intent);
                } else {
                    Log.d("Error", "Post book failure");
                }
            }

            private void postCoverImg(MultipartBody.Part body, Long id) {
                APIService.API_SERVICE.postCoverImg(body, id).enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.isSuccessful()) {
                            Log.d("Error", "Success");
                        } else {
                            Log.d("Error", "Failure " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Log.d("Error", "Failure " + t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    //kiem tra quyen va xin cap quyen
    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGalery();
            return;}
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGalery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, REQUEST_CODE);
        }
    }
    //xac nhan ket qua xin cap quyen và mo thu vien
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGalery();
            }}
    }
    //mo thu vien
    private void openGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Image"));
    }


}