package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import app.demo.Fragment.LoadFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        Boolean isLogged = sharedPreferences.getBoolean("isLogged", false);
        if(isLogged)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            getSupportFragmentManager().beginTransaction().add(R.id.frm_container, new LoadFragment()).commit();
        }
    }
}