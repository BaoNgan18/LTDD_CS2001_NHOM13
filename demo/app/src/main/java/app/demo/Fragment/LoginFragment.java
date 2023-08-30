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

                findUserByEmail(strEmail);
            }

            private void findUserByEmail(String strEmail) {
                APIService.API_SERVICE.findUserByEmail(strEmail).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User u = response.body();
                            if (u != null) {
                                Log.d("Error", u.toString());
                                if (u.getPassword().equals(strPassword)) {
                                    editor.putBoolean("isLogged", true);
                                    Gson gson = new Gson();
                                    String userJson = gson.toJson(u);
                                    editor.putString("user", userJson);
                                    editor.apply();

                                    Toast.makeText(getView().getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    tvMesage.setVisibility(View.VISIBLE);
                                }
                            }
                        } else
                            Log.d("Error", "Khong co phan hoi");
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("Error", "Khong thuc hien");
                    }
                });
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction frmTransaction = getChildFragmentManager().beginTransaction();
                frmTransaction.replace(R.id.frm_login, new RegisterFragment()).commit();
            }
        });
        return view;
    }
}