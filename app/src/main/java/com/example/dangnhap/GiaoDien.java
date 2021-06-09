package com.example.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiaoDien extends AppCompatActivity {
    ArrayList<User> arrayList;
    ListView listView;
    UserRecyler userRecyler;
    EditText ten,tuoi,dep;
    Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien);
        listView = findViewById(R.id.listview);
        ten = findViewById(R.id.addten);
        tuoi = findViewById(R.id.addtuoi);
        dep = findViewById(R.id.addDep);
        String url = "https://60b5aee7fe923b0017c84656.mockapi.io/users";
        GetData(url);
        Add = findViewById(R.id.addbtn);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi(url);
                GetData(url);
            }
        });

    }
    public void GetData(String url) {
        arrayList = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayList.add(new User(object.getInt("id"),
                                        object.getString("Name"),
                                        object.getInt("Age"),
                                        object.getString("Dep")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        userRecyler = new UserRecyler(GiaoDien.this,arrayList,R.layout.item);
                        listView.setAdapter(userRecyler);
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GiaoDien.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(GiaoDien.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GiaoDien.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Name", ten.getText().toString());
                params.put("Age", tuoi.getText().toString());
                params.put("Dep", dep.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}