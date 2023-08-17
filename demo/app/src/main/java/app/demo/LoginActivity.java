package app.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.demo.Fragment.LoadFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().add(R.id.frm_container, new LoadFragment());
    }
}