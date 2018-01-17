package com.example.lanceshu.bestvegetable.Fragemnet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanceshu.bestvegetable.Adapter.OrderAdapter;
import com.example.lanceshu.bestvegetable.Content;
import com.example.lanceshu.bestvegetable.DataBase.MyDataBaseHelper;
import com.example.lanceshu.bestvegetable.DataBean.ProductBean;
import com.example.lanceshu.bestvegetable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2018/1/9.
 */

public class OrderFragment extends Fragment{

    private View view;
    private TextView textView;
    private LinearLayout orderLayout;
    private RecyclerView orderList;

    private MyDataBaseHelper myDataBaseHelper;
    private SQLiteDatabase database;

    private List<ProductBean> productBeanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_layout,container,false);

        myDataBaseHelper = new MyDataBaseHelper(getContext(),"BestVegetableDB.db",null,1);
        database = myDataBaseHelper.getWritableDatabase();

        productBeanList = new ArrayList<>();
        productBeanList.clear();

        initWight(view);
        return view;
    }

    private void initWight(View view) {
        textView = view.findViewById(R.id.other_layout);
        orderLayout = view.findViewById(R.id.orderLayout);

        if(!Content.isLogin){
            textView.setVisibility(View.VISIBLE);
            orderLayout.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.GONE);
            orderLayout.setVisibility(View.VISIBLE);
            orderList = view.findViewById(R.id.orderList);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            orderList.setLayoutManager(manager);
            queryOrders();
            OrderAdapter adapter = new OrderAdapter(getContext(),productBeanList);
            orderList.setAdapter(adapter);
        }

    }

    private void queryOrders() {
        Cursor cursor = database.query("pOrder",null,null ,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(cursor.getColumnIndex("puser")).equals("root")){
                    ProductBean productBean = new ProductBean();
                    productBean.setPName(cursor.getString(cursor.getColumnIndex("pname")));
                    productBean.setPPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex("pprice"))));
                    productBean.setPNum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("pnum"))));
                    productBean.setPImagfile(cursor.getString(cursor.getColumnIndex("pimage")));
                    productBean.setPUnit("斤");
                    productBeanList.add(productBean);
                }
            }while(cursor.moveToNext());
            cursor.close();
        }
    }
}
