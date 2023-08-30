package app.demo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.demo.API.APIService;
import app.demo.R;
import app.demo.MainActivity;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    EditText edtEmail, edtPassWord;
    TextView tvMesage;
    List<User> listUser;
    String strEmail, strPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        Button btnRegister = (Button) view.findViewById(R.id.btn_register);

        edtEmail = view.findViewById(R.id.edt_email);
        edtPassWord = view.findViewById(R.id.edt_password);
        tvMesage = view.findViewById(R.id.tv_message);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = edtEmail.getText().toString();
                strPassword = edtPassWord.getText().toString();
                User us = new User(strPassword, strEmail, "");

                loginAccount(us);
            }

            private void loginAccount(User us) {
                APIService.API_SERVICE.loginAccount(us).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getView().getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            User newUser = response.body();
                            editor.putBoolean("isLogged", true);
                            Gson gson = new Gson();
                            String userJson = gson.toJson(newUser);
                            editor.putString("user", userJson);
                            editor.apply();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("Error", response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("Error", "Khong thuc hien");
                    }
                });
            }
        });
        return view;
    }
}