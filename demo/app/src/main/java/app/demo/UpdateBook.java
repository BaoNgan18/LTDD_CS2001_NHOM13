package app.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.SpinnerGenres;
import app.demo.model.Book;
import app.demo.model.Genre;
import app.demo.model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateBook extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;

    AlertDialog dialog;
    TextView tvCancel, tvPost;
    String strName, strDescribe, strGenre, msg;
    ImageView imgCoverBook;
    EditText edtNameBook, edtDescribe, edtGenre;
    User user;
    Book book, newBook, oldBook;
    Button btnSubmit;
    List<Genre> genres, listGenre, mGenres;
    Uri mUri;
    Toolbar toolbar;
    MultipartBody.Part body;
    Spinner spGenre;

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
        btnSubmit.setVisibility(View.GONE);
        toolbar = findViewById(R.id.toolBar);
        toolbar.setVisibility(View.VISIBLE);

        edtNameBook = findViewById(R.id.edt_namePost);
        edtDescribe = findViewById(R.id.edt_describePost);
        imgCoverBook = findViewById(R.id.img_post_cover);
        tvPost = findViewById(R.id.tv_post);
        tvCancel = findViewById(R.id.tv_cancel);
        spGenre = findViewById(R.id.sp_genre);

        oldBook = (Book) getIntent().getSerializableExtra("book");
        Log.d("Error", oldBook.toString());
        edtNameBook.setText(oldBook.getNameBook());
        edtDescribe.setText(oldBook.getDescribe());

        StringBuilder sb = new StringBuilder();
        oldBook.getListGenre().forEach(t -> sb.append(t.getNameOfGenre() + ", "));
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        edtGenre.setText(sb);

        String coverImgUrl = oldBook.getCoverImg();
        Glide.with(getApplicationContext())
                .load(coverImgUrl)
                .into(imgCoverBook);

        mGenres = new ArrayList<>();
        genres = new ArrayList<>();
        getListGenre();
        tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtNameBook.getText().toString();
                strDescribe = edtDescribe.getText().toString();
                newBook = new Book(strName, listGenre, strDescribe, user);
                updateBook(oldBook.getId(), newBook);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });

        imgCoverBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }

    private void updateBook(Long id, Book newBook) {
        APIService.API_SERVICE.updateBook(id, newBook).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Log.d("Error", "Update book success");
                    Book newBook = response.body();
                    postCoverImg(body, newBook.getId());
                } else {
                    Log.d("Error", "Update book failure");
                }
            }

            private void postCoverImg(MultipartBody.Part body, Long id) {
                APIService.API_SERVICE.postCoverImg(body, id).enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.isSuccessful()) {
                            Log.d("Error", "Update image success");
                        } else {
                            Log.d("Error", "Update image failure");
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

//    private void postCoverImg(MultipartBody.Part file, Long id) {
//        APIService.API_SERVICE.postCoverImg(file, id).enqueue(new Callback<Book>() {
//            @Override
//            public void onResponse(Call<Book> call, Response<Book> response) {
//                if (response.isSuccessful())
//                    Log.d("Error", "upload image success");
//                else
//                    Log.d("Error", "upload image failure");
//            }
//
//            @Override
//            public void onFailure(Call<Book> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//            }
//        });
//    }


    private void getListGenre() {
        APIService.API_SERVICE.getAllGenre().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (response.isSuccessful()) {
                    genres.addAll(response.body());
                  if (genres.isEmpty()) {
                        Log.d("Error", "khong co listChapter");
                    } else {
                        SpinnerGenres adapter = new SpinnerGenres(getApplicationContext(), R.layout.spinner_genre, genres);
                        spGenre.setAdapter(adapter);
//                        spGenre.setSelection(0);
                        spGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Genre g = (Genre) spGenre.getItemAtPosition(i);
                                mGenres.add(g);
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    //chon anh tu thu vien
    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGalery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGalery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGalery();
            }
        }
    }

    private void openGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Image"));
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

    private void convertImage(){
        String url = RealPathUtil.getRealPath(UpdateBook.this, mUri);
        Log.d("Error", url);
        File file = new File(url);

        Log.d("File", file.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
    }
}