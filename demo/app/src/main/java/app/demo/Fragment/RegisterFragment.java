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


        edtEmail = rootView.findViewById(R.id.edt_email);
        edtUserName = rootView.findViewById(R.id.edt_user_name);
        edtPassword = rootView.findViewById(R.id.edt_password);
        tvMessage = rootView.findViewById(R.id.tv_message);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.toString();
                String strUserName = edtUserName.toString();
                String strPassword = edtPassword.toString();

                findUserByEmail(strEmail);
                if(user != null){
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setText("Email đã tồn tại");
                } else
                {
                    Toast.makeText(getView().getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    createUser();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frm_register, new LoginFragment()).commit();
                }
            }
        });




        return rootView;

    }

    private void createUser() {
        APIService.API_SERVICE.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void findUserByEmail(String email) {
        APIService.API_SERVICE.findUserByEmail(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private void getAllUser() {
//        APIService.API_SERVICE.getAllUser().enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                listUser.addAll(response.body());
//            }
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(getView().getContext(), "That bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}