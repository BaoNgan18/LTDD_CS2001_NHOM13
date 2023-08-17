package app.demo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.demo.R;

public class RegisterFragment extends Fragment {


    String edtEmail, edtUserName, edtPassword, edtRePassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);


        Button btnLogin = (Button) rootView.findViewById(R.id.btn_login);
        Button btnRegister = (Button) rootView.findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frm_register, new LoginFragment()).commit();
            }
        });


        edtEmail = rootView.findViewById(R.id.edt_email).toString();
        edtUserName = rootView.findViewById(R.id.edt_user_name).toString();
        edtPassword = rootView.findViewById(R.id.edt_password).toString();
        edtRePassword = rootView.findViewById(R.id.edt_re_password).toString();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvInfo = rootView.findViewById(R.id.info);
                String str = String.format("%s %s %s %s", edtEmail, edtUserName, edtPassword, edtRePassword);
                tvInfo.setText(str);
            }
        });




        return rootView;

    }
}