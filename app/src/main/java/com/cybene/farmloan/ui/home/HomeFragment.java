package com.cybene.farmloan.ui.home;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cybene.farmloan.R;
import com.cybene.farmloan.utils.AppConfig;
import com.cybene.farmloan.utils.adapter.DetailsTableAdapter;
import com.cybene.farmloan.utils.items.Detail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    private TableLayout items;
    TextView id,name,ppn,spn,email,idno,btype,loc,monthly,status,created;
    View tableRow;
    LinearLayout content;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        items = root.findViewById(R.id.container);
        content = root.findViewById(R.id.content);
        getData();
        return root;
    }

    private void getData() {
        final ArrayList <Detail> drinks = new ArrayList<>();
        final RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(getActivity(),
                20, 60, ContextCompat.getColor(getActivity(), R.color.red));
        loader.setAnimDuration(3000);
        content.addView(loader);
        StringRequest drinkStringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject;
                        try{
                            JSONObject drinkObject = new JSONObject(response);
                            JSONArray drinkArray = drinkObject.getJSONArray("data");
                            for(int i=0; i<drinkArray.length();i++){
                                jsonObject = drinkArray.getJSONObject(i);
                                tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.table_item, null, false);
                                //table components
                                id = tableRow.findViewById(R.id.id);
                                name = tableRow.findViewById(R.id.name);
                                ppn = tableRow.findViewById(R.id.ppn);
                                spn = tableRow.findViewById(R.id.spn);
                                email = tableRow.findViewById(R.id.email);
                                idno = tableRow.findViewById(R.id.idNo);
                                btype = tableRow.findViewById(R.id.btype);
                                loc = tableRow.findViewById(R.id.loc);
                                monthly = tableRow.findViewById(R.id.monthly);
                                status = tableRow.findViewById(R.id.status);
                                created = tableRow.findViewById(R.id.created);
                                id.setText(String.valueOf(jsonObject.getInt("id")));
                                name.setText(jsonObject.getString("name"));
                                ppn.setText(jsonObject.getString("primaryphone"));
                                spn.setText(jsonObject.getString("secondaryphone"));
                                email.setText(jsonObject.getString("email"));
                                idno.setText(String.valueOf(jsonObject.getInt("idno")));
                                btype.setText(jsonObject.getString("businesstype"));
                                loc.setText(jsonObject.getString("location"));
                                monthly.setText(jsonObject.getString("monthlyrevenue"));
                                created.setText(jsonObject.getString("reg_date"));
                                status.setText(String.valueOf(jsonObject.getInt("status")));
                                items.addView(tableRow);
                            }
                            content.removeView(loader);

                        }catch (JSONException e){
                            content.removeView(loader);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        content.removeView(loader);
                        error.printStackTrace();
                    }
                });
        RequestQueue requestQue = Volley.newRequestQueue(getActivity());
        requestQue.add(drinkStringRequest);
    }
}