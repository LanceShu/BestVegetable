package com.example.lanceshu.bestvegetable.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lanceshu.bestvegetable.DataBean.ProductBean;
import com.example.lanceshu.bestvegetable.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lanceshu on 17-12-7.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductBean> productBeans;
    private Context context;

    public ProductAdapter(List<ProductBean> productBeans,Context context){
        this.productBeans = productBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ProductBean productBean = productBeans.get(position);
        holder.proName.setText(productBean.getPName());
        holder.proPrice.setText("单价： "+productBean.getPPrice()+"元/"+productBean.getPUnit());
        Glide.with(context)
                .load(productBean.getPImagfile())
                .asBitmap()
                .centerCrop()
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,productBean.getPName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView imageView;
        private TextView proName;
        private TextView proPrice;

        public ViewHolder(View view) {
            super(view);
            cardView =  view.findViewById(R.id.cardview);
            imageView = view.findViewById(R.id.product_imag);
            proName = view.findViewById(R.id.product_name);
            proPrice = view.findViewById(R.id.product_price);
        }
    }
}
