package com.example.lanceshu.bestvegetable.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lanceshu.bestvegetable.Content;
import com.example.lanceshu.bestvegetable.DataBean.ProductBean;
import com.example.lanceshu.bestvegetable.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lanceshu on 17-12-7.
 */

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, int position) {
        ViewHolder holder = (ViewHolder) viewholder;
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
                if(Content.isLogin){
                    Dialog dialog = new Dialog(context,R.style.DialogTheme);
                    showDialog(dialog,productBean.getPImagfile(),productBean.getPName(),productBean);
                }else{
                    Toast.makeText(context,"您还未登录~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDialog(final Dialog dialog, String pImagfile, final String name, final ProductBean productBean) {
        dialog.setContentView(R.layout.vegetable_infor);
        dialog.setCanceledOnTouchOutside(true);

        TextView textView = dialog.findViewById(R.id.infor_text);
        ImageView image  = dialog.findViewById(R.id.infor_image);
        final EditText editText = dialog.findViewById(R.id.infor_edit);
        Button button = dialog.findViewById(R.id.infor_but);

        textView.setText(name);

        Glide.with(context)
                .load(pImagfile)
                .centerCrop()
                .into(image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,name + "已下单" + editText.getText().toString() + "斤"
                        ,Toast.LENGTH_SHORT).show();
                productBean.setPNum(Double.parseDouble(editText.getText().toString()));
                Content.productBeans.add(productBean);
                dialog.dismiss();
            }
        });

        dialog.show();
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
