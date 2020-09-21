package com.cybene.farmloan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cybene.farmloan.R;
import com.cybene.farmloan.utils.AppConfig;
import com.cybene.farmloan.utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewAccountActivity extends AppCompatActivity {
    EditText name,primaryphone,secondaryphone,email,idno,businesstype,location,monthlyrevenue;
    Button save;
    ConstraintLayout container;
    private static final String TAG = NewAccountActivity.class.getSimpleName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        name = findViewById(R.id.name);
        primaryphone =  findViewById(R.id.primaryPhone);
        secondaryphone = findViewById(R.id.secondaryPhone);
        email = findViewById(R.id.email);
        idno = findViewById(R.id.idNumber);
        businesstype  = findViewById(R.id.businessType);
        location = findViewById(R.id.location);
        monthlyrevenue = findViewById(R.id.monthly);
        save = findViewById(R.id.saveAccountButton);
        container = findViewById(R.id.container);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname  = name.getText().toString().trim();
                final String pphone = primaryphone.getText().toString().trim();
                final String sphone = secondaryphone.getText().toString().trim();
                final String emailval = email.getText().toString().trim();
                final String idnumber = idno.getText().toString().trim();
                final String business = businesstype.getText().toString().trim();
                final String loc = location.getText().toString().trim();
                final String monthly = monthlyrevenue.getText().toString().trim();
                save(fullname, pphone, sphone, emailval, idnumber, business, loc, monthly);

            }
        });
    }

    private void save(final String fullname, final String pphone, final String sphone, final String email, final String idnumber, final String business, final String loc, final String monthly) {
        String tag_string_req = "req_register";
        final RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(this,
                20, 60, ContextCompat.getColor(this, R.color.red));
        loader.setAnimDuration(3000);
        container.addView(loader);
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "New Account Response: " + response);
                container.removeView(loader);
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        Toast.makeText(getApplicationContext(), "Account successfully created.", Toast.LENGTH_LONG).show();
                        // Launch login activity
                        Intent intent = new Intent(NewAccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), " An error has occurred during account creation "+error.getMessage(), Toast.LENGTH_LONG).show();
                container.removeView(loader);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("fname", fullname);
                params.put("ppn", pphone);
                params.put("spn", sphone);
                params.put("email", email);
                params.put("idno", idnumber);
                params.put("bt", business);
                params.put("loc",loc);
                params.put("mrev", monthly);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}