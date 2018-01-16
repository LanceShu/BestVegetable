package com.example.lanceshu.bestvegetable.Fragemnet;

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
import com.example.lanceshu.bestvegetable.R;

/**
 * Created by Lance on 2018/1/9.
 */

public class OrderFragment extends Fragment{

    private View view;
    private TextView textView;
    private LinearLayout orderLayout;
    private RecyclerView orderList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_layout,container,false);
        initWight(view);
        return view;
    }

    private void initWight(View view) {
        textView = view.findViewById(R.id.other_layout);
        orderLayout = view.findViewById(R.id.orderLayout);

        if(Content.productBeans.size() == 0){
            textView.setVisibility(View.VISIBLE);
            orderLayout.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.GONE);
            orderLayout.setVisibility(View.VISIBLE);
            orderList = view.findViewById(R.id.orderList);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            orderList.setLayoutManager(manager);
            OrderAdapter adapter = new OrderAdapter(getContext(),Content.productBeans);
            orderList.setAdapter(adapter);
        }

    }
}
