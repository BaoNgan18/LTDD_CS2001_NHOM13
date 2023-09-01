package app.demo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.MainActivity;
import app.demo.R;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText edtEmail, edtUserName, edtPassword;
    List<User> listUser;
    TextView tvMessage;
    User user;
    String strEmail, strUserName, strPassword;
    Boolean isUser = false;

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
        edtEmail = rootView.findViewById(R.id.edt_email);
        edtUserName = rootView.findViewById(R.id.edt_user_name);
        edtPassword = rootView.findViewById(R.id.edt_password);
        tvMessage = rootView.findViewById(R.id.tv_message);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = edtEmail.getText().toString();
                strUserName = edtUserName.getText().toString();
                strPassword = edtPassword.getText().toString();

                findUserByEmail(strEmail);

            }

            private void findUserByEmail(String strEmail) {
                APIService.API_SERVICE.findUserByEmail(strEmail).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            user = response.body();
                            if (user != null) {
                                tvMessage.setVisibility(View.VISIBLE);
                                tvMessage.setText("Email đã tồn tại");
                            }
                        } else {
                            User newUser = new User(strPassword, strEmail, strUserName);
                            createUser(newUser);
                            Toast.makeText(getView().getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frm_register, new LoginFragment()).commit();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frm_register, new LoginFragment()).commit();
            }
        });
        return rootView;

    }

    private void createUser(User user) {
        APIService.API_SERVICE.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d("Error", "Success");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}