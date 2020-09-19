package com.cybene.farmloan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.cybene.farmloan.R;
import com.cybene.farmloan.utils.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewAccountActivity extends AppCompatActivity {
    EditText name,primaryphone,secondaryphone,idno,businesstype,location,monthlyrevenue;
    Button save;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        name = findViewById(R.id.name);
        primaryphone =  findViewById(R.id.primaryPhone);
        secondaryphone = findViewById(R.id.secondaryPhone);
        idno = findViewById(R.id.idNumber);
        businesstype  = findViewById(R.id.businessType);
        location = findViewById(R.id.location);
        monthlyrevenue = findViewById(R.id.monthly);
        save = findViewById(R.id.saveAccountButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname  = name.getText().toString().trim();
                final String pphone = primaryphone.getText().toString().trim();
                final String sphone = secondaryphone.getText().toString().trim();
                final String idnumber = idno.getText().toString().trim();
                final String business = businesstype.getText().toString().trim();
                final String monthly = monthlyrevenue.getText().toString().trim();
                save(fullname, pphone, sphone, idnumber, business, monthly);

            }
        });
    }

    private void save(String fullname, String pphone, String sphone, String idnumber, String business, String monthly) {
        String tag_string_req = "req_register";
        pDialog.setMessage("Saving ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response);
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String final_uid = jObj.getString("uid");
                        JSONObject user = jObj.getJSONObject("user");
                        String l_name = user.getString("name");
                        String ot_name = user.getString("oname");
                        String final_email = user.getString("email");
                        String final_phone = user.getString("phone");
                        String final_id = user.getString("id");
                        String final_account = user.getString("account");
                        String final_acType = user.getString("account_type");
                        String final_created_at = user.getString("created_at");
                        // Inserting row in users table
                        db.addUser(l_name, ot_name, final_email, final_phone, final_id, final_uid, final_account, final_acType, final_created_at);
                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
                        // Launch login activity
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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
                Toast.makeText(getApplicationContext(), " An error has occurred during registration "+error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("lname", lName);
                params.put("oname", oName);
                params.put("email", email);
                params.put("phone", phone);
                params.put("idno", id);
                params.put("password", pass);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}