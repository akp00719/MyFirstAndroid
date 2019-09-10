package com.example.project;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<ProductPojo> productPojoList;
    RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,2));

        productPojoList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        String url = "http://show.planet24by7.com/app_php/upcomming.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObjectResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonObjectResponse.getJSONArray("response");
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        Log.d("yoyo","data parsed  "+name);
                        String description = jsonObject.getString("description");
                        String genreType = jsonObject.getString("genretype");
                        String type = jsonObject.getString("type");
                        String imageURLraw = jsonObject.getString("image1");
                        String imageURL = "http://show.planet24by7.com/pic/"+imageURLraw.substring(4);
                        String language = jsonObject.getString("language");
                        productPojoList.add(new ProductPojo( id, name, description, genreType, type, imageURL, language));
                    }
                    if(productPojoList.size()>0){
                        ProductAdapter adapter = new ProductAdapter(MainActivity.this, productPojoList);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", Objects.requireNonNull(error.getMessage()));
            }
        });
        requestQueue.add(stringRequest);

//        ProductAdapter adapter = new ProductAdapter(this, productPojoList);
//        recyclerView.setAdapter(adapter);
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
