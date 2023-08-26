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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

//    SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();


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

        edtEmail = view.findViewById(R.id.edt_email);
        edtPassWord = view.findViewById(R.id.edt_password);
        tvMesage = view.findViewById(R.id.tv_message);
        listUser = new ArrayList<>();
        getAllUser();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUserName = edtEmail.getText().toString().trim();
                String strPassword = edtPassWord.getText().toString().trim();

                boolean isUser = false;

                for (User user: listUser){
                    if(strUserName.equals(user.getEmail()) && strPassword.equals(user.getPassword())){
                        isUser = true;
                        break;
                    }
                }

                if(isUser){


                    Toast.makeText(getView().getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else
                {
                    tvMesage.setVisibility(View.VISIBLE);
                }
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

    private void getAllUser() {
        APIService.API_SERVICE.getAllUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listUser.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "That bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}