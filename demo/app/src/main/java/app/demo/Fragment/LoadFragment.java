package app.demo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.demo.MainActivity;
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

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean isLogged = sharedPreferences.getBoolean("isLogged", false);
        if(isLogged){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        else {
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
        }


//        rootView.getSupportFragmentManager().beginTransaction().replace(R.id.frmLoad, new LoadFragment()).commit();
        return rootView;
    }
}