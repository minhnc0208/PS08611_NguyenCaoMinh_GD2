package com.example.ps08611_nguyencaominh_gd1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ps08611_nguyencaominh_gd1.Activity.SplashScreen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Toolbar tb;
    Button btnOK, btnCancel;
    EditText txtName, txtEmail, txtPass;
    // String registerUrl = "http://192.168.1.131/MOB403/index.php";
   // String registerUrl = "http://192.168.1.131/MOB403/index.php";
    String registerUrl = "http://10.82.128.158/MOB403/index.php";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);
        txtName = findViewById(R.id.edtName);
        txtEmail = findViewById(R.id.edtUser);
        txtPass = findViewById(R.id.edtPass);
        tb = findViewById(R.id.tb_dangkytaikhoan);
        tb.setNavigationIcon(R.drawable.ic_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, SplashScreen.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, registerUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyRegister(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegisterActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi", error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();

                        param.put("name", txtName.getText().toString());
                        param.put("email", txtEmail.getText().toString());
                        param.put("password", txtPass.getText().toString());
                        param.put("tag", "register");

                        return param;
                    }
                };
                Volley.newRequestQueue(RegisterActivity.this).add(stringrequest);

            }
        });
    }

    private void xulyRegister(String response) {

        String thanhcong = "";
        try {
            JSONObject jsonobject = new JSONObject(response);
            thanhcong = jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if (Integer.parseInt(thanhcong) == 1)//thanh cong
            {
                Toast.makeText(this, "Register Done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            } else //that bai
            {
                Toast.makeText(this, "Register Fail", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}