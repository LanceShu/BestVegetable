package com.example.lanceshu.bestvegetable.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lanceshu.bestvegetable.DataBean.ProductBean;
import com.example.lanceshu.bestvegetable.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Lance on 2018/1/16.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductBean> productBeans;
    private Context context;

    public OrderAdapter(Context context,List<ProductBean> productBeans){
        this.productBeans = productBeans;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderViewHolder viewHolder = (OrderViewHolder) holder;
        ProductBean productBean = productBeans.get(position);
        Glide.with(context)
                .load(productBean.getPImagfile())
                .asBitmap()
                .centerCrop()
                .into(viewHolder.orderImage);
        viewHolder.orderName.setText(productBean.getPName());
        viewHolder.orderPrice.setText(productBean.getPPrice()+"元/"+productBean.getPUnit());
        viewHolder.orderNum.setText(productBean.getPNum()+"斤");
        double total = productBean.getPNum() * productBean.getPPrice();
        viewHolder.orderTotal.setText("总共： " + new DecimalFormat("#.00").format(total) + " 元");
    }

    @Override
    public int getItemCount() {
        return productBeans.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder{

        private ImageView orderImage;
        private TextView orderName;
        private TextView orderPrice;
        private TextView orderNum;
        private TextView orderTotal;

        public OrderViewHolder(View view) {
            super(view);
            orderImage = view.findViewById(R.id.orderImage);
            orderName = view.findViewById(R.id.orderName);
            orderPrice = view.findViewById(R.id.orderPrice);
            orderNum = view.findViewById(R.id.orderNum);
            orderTotal = view.findViewById(R.id.orderTotal);
        }
    }
}
