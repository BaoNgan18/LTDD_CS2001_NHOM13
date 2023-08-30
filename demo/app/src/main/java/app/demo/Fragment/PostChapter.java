package app.demo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import app.demo.API.APIService;
import app.demo.R;
import app.demo.model.Book;
import app.demo.model.Chapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostChapter extends Fragment {

    TextView tvPost, tvCancel;
    EditText edtNameChapter, edtContent;
    String strNameChapter, strContent;
    Book book;
    Chapter chapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_chapter, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null){
            book = (Book) bundle.getSerializable("NewBook");
        }


        tvPost = view.findViewById(R.id.tv_post);
        tvCancel = view.findViewById(R.id.tv_cancel);
        edtContent = view.findViewById(R.id.edt_content_chapter);
        edtNameChapter = view.findViewById(R.id.edt_name_chapter);

        strContent = edtContent.getText().toString();
        strNameChapter = edtNameChapter.getText().toString();

        Chapter chapter1 = new Chapter(book, strNameChapter, strContent);

        tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createChapter(book.getId(), chapter1);
            }
        });

        return view;
    }

    private void createChapter(Long id, Chapter chapter1) {
        APIService.API_SERVICE.createChapter(id, chapter1).enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if(response.isSuccessful())
                    Log.d("Error", "post chapter success");
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}