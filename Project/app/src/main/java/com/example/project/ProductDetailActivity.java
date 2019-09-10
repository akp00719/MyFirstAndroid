package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class ProductDetailActivity extends AppCompatActivity {

    TextView textViewProductName, textViewProductDesc;
    ImageView imageViewProduct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        textViewProductDesc = findViewById(R.id.textViewProductDesc);
        textViewProductName = findViewById(R.id.textViewProductName);
        imageViewProduct = findViewById(R.id.imageViewProduct);

        Intent iGet = getIntent();
        textViewProductName.setText(iGet.getStringExtra("name"));
        textViewProductDesc.setText(iGet.getStringExtra("desc"));
        Picasso.get().load(iGet.getStringExtra("img")).into(imageViewProduct);
    }
}
