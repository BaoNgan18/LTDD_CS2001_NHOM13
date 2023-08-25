package app.demo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.demo.R;
import app.demo.MainActivity;

public class LoginFragment extends Fragment {
    EditText edtUserName, edtPassWord;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        Button btnRegister = (Button) view.findViewById(R.id.btn_register);

        edtUserName = view.findViewById(R.id.edt_userName);
        edtPassWord = view.findViewById(R.id.edt_password);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction frmTransaction = getChildFragmentManager().beginTransaction();
                frmTransaction.replace(R.id.frm_login, new RegisterFragment()).commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}