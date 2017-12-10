package com.example.lanceshu.bestvegetable.Fragemnet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lanceshu.bestvegetable.Adapter.ProductAdapter;
import com.example.lanceshu.bestvegetable.Content;
import com.example.lanceshu.bestvegetable.R;
import static com.example.lanceshu.bestvegetable.Content.*;

/**
 * Created by lanceshu on 17-12-7.
 */

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        initData();
        initWight(view);
        return view;
    }

    private void initData() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        adapter = new ProductAdapter(Content.products,getContext());
                        recyclerView.setAdapter(adapter);
                        break;
                }
            }
        };
    }

    private void initWight(View view) {
        recyclerView = view.findViewById(R.id.home_recycler);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        if(Content.products != null){
            adapter = new ProductAdapter(Content.products,getContext());
            recyclerView.setAdapter(adapter);
        }
    }
}
