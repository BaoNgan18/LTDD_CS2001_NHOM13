package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.Adapter.TruyenAdapter;
import app.demo.model.Truyen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvTruyen;
    List<Truyen> truyenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvTruyen = findViewById(R.id.rcv_truyen);
        rcvTruyen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        truyenList = new ArrayList<>();
        CallAPI();
    }

    public void  CallAPI(){
        APIService.API_SERVICE.layDSTruyen().enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                truyenList.addAll(response.body());
                TruyenAdapter truyenAdapter = new TruyenAdapter(truyenList);
                rcvTruyen.setAdapter(truyenAdapter);
                Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}