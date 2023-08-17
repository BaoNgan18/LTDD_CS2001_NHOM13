package app.demo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.demo.R;


public class LoadFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_load, container, false);


//        rootView.getSupportFragmentManager().beginTransaction().replace(R.id.frmLoad, new LoadFragment()).commit();
        Button btnLogin = (Button) rootView.findViewById(R.id.btn_login);
        Button btnRegister = (Button) rootView.findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frm_load, new LoginFragment()).commit();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction frmTransaction = getChildFragmentManager().beginTransaction();
                frmTransaction.replace(R.id.frm_load, new RegisterFragment()).commit();
            }
        });
        return rootView;
    }
}