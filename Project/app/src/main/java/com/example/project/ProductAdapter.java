package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<ProductPojo> productPojoList;

    ProductAdapter(Context context, List<ProductPojo> productPojoList) {
        this.context = context;
        this.productPojoList = productPojoList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final ProductPojo productPojo = productPojoList.get(position);

            holder.textViewTitle.setText(productPojo.getName());
            holder.textViewType.setText(productPojo.getType());
            holder.textViewGenreType.setText(productPojo.getGenreType());
            holder.textViewLanguage.setText(productPojo.getLanguage());
            Picasso.get().load(productPojo.getImageURL()).into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProductDetailActivity.class);
                    intent.putExtra("img",productPojo.getImageURL());
                    intent.putExtra("name",productPojo.getName());
                    intent.putExtra("desc",productPojo.getDescription());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return productPojoList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewType, textViewGenreType, textViewLanguage;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImage);
            textViewTitle = itemView.findViewById(R.id.productName);
            textViewType = itemView.findViewById(R.id.type);
            textViewGenreType = itemView.findViewById(R.id.genreType);
            textViewLanguage = itemView.findViewById(R.id.language);
        }
    }
}
