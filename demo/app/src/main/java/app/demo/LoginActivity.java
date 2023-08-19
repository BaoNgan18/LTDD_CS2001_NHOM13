package app.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import app.demo.Fragment.LoadFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getSupportFragmentManager().beginTransaction().add(R.id.frm_container, new LoadFragment());

        FragmentTransaction frmTransaction = getSupportFragmentManager().beginTransaction();

        frmTransaction.add(R.id.frm_container, new LoadFragment()).commit();
    }
}