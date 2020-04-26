
package com.rkrzmail.mobilesales.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.rkrzmail.mobilesales.APIService.APIClient;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.NavigationActivity;
import com.rkrzmail.mobilesales.R;
import com.rkrzmail.mobilesales.model.Login.ModelLogin;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSlide2 extends AppCompatActivity {

    ModelLogin dataModelUser;
    SharedPreferences pref;
    EditText un, pw;
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layar2);
        un = findViewById(R.id.username);
        pw = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params1 = new HashMap<String, String>();
                params1.put("username", un.getText().toString());
                Map<String, String> params2 = new HashMap<String, String>();
                params2.put("password", pw.getText().toString());
                if ((un.getText().toString() != null) && (pw.getText().toString() != null)) {
                    //There is a mapping for username and password.
                    getData(params1, params2);
                }
            }
        });
    }

    public void getData(Map<String, String> params1, Map<String, String> params2) {
        final APIInterfacesRest apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        final Call<ModelLogin> data = apiInterface.getData(params1, params2);
        data.enqueue(new Callback<ModelLogin>() {
            @Override
            public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                dataModelUser = response.body();
                if (dataModelUser != null){
                    pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    pref.edit().putString("name", dataModelUser.getData().getName().toString()).commit();
                    pref.edit().putString("branch", dataModelUser.getData().getBranch().toString()).commit();
                    pref.edit().putString("unit", dataModelUser.getData().getUnit().toString()).commit();
                    pref.edit().putString("position", dataModelUser.getData().getPosition().toString()).commit();
                    pref.edit().putString("nik", dataModelUser.getData().getNIK().toString()).commit();
                    pref.edit().putString("mscode", dataModelUser.getData().getSMCode().toString()).commit();
                    Intent a = new Intent(getApplicationContext(), NavigationActivity.class);
                    startActivity(a);
                    //Toast.makeText(getApplicationContext(), "Koneksi Berhasil", Toast.LENGTH_SHORT).show();
                } else
                {
                    // getData(params1, params2);
                    Toast.makeText(getApplicationContext(),
                            "login gagal",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Terjadi gangguan koneksi", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
