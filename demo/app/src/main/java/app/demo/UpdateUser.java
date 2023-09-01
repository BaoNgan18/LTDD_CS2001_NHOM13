package app.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

import app.demo.API.APIService;
import app.demo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity {

    EditText edtName, edtPass, edtEmail;
    String strName, strPass, strEmail;
    Button btnLogout, btnDelete, btnUpdate;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userJson = sharedPreferences.getString("user", "");
        if (userJson.isEmpty()) {
            Log.d("Error", "UserJson null");
        } else {
            Gson gson = new Gson();
            user = gson.fromJson(userJson, User.class);
        }

        edtName = findViewById(R.id.edt_name);
        edtPass = findViewById(R.id.edt_pass);
        edtEmail = findViewById(R.id.edt_email);
        btnLogout = findViewById(R.id.btn_logout);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);

        edtName.setText(user.getUserName());
        edtPass.setText(user.getPassword());
        edtEmail.setText(user.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strName = edtName.getText().toString();
                strPass = edtPass.getText().toString();
                strEmail = edtEmail.getText().toString();

                User u = new User(strPass, strEmail, strName);
                updateUser(user.getId(), u);
            }

            private void updateUser(long id, User u) {
                APIService.API_SERVICE.updateUser(id, u).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User newUser = (User) response.body();
                            Gson gson = new Gson();
                            String userJson = gson.toJson(newUser);
                            editor.putString("user", userJson);
                            editor.apply();
                            Log.d("Error", "Update Succes");
                            onBackPressed();
                        } else
                            Log.d("Error", "Update failure");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("user");
                editor.remove("isLogged");
                editor.apply();

                Intent intent = new Intent(UpdateUser.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(user.getId());
            }

            private void deleteUser(long id) {
                APIService.API_SERVICE.deleteUser(id).enqueue(new Callback<Map<String, Boolean>>() {
                    @Override
                    public void onResponse(Call<Map<String, Boolean>> call, Response<Map<String, Boolean>> response) {
                        if (response.isSuccessful()) {
                            editor.remove("user");
                            editor.remove("isLogged");
                            editor.apply();

                            Intent intent = new Intent(UpdateUser.this, LoginActivity.class);
                            startActivity(intent);
                            Log.d("Error", "Delete success");
                        } else {
                            Log.d("Error", "Delete failure " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Boolean>> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        });
    }


}