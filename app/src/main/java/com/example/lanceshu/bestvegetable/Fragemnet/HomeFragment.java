package com.example.lanceshu.bestvegetable.Fragemnet;

import android.os.Bundle;
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
import com.example.lanceshu.bestvegetable.Utils.GetProductInfo;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
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

        if(Content.INSTANCE.getProducts().size() == 0){
            GetProductInfo.INSTANCE.getInfo(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Content.INSTANCE.setProducts(GetProductInfo.INSTANCE.handleProductInfo(response.body().string()));
                    adapter = new ProductAdapter(Content.INSTANCE.getProducts(),getContext());
                    recyclerView.setAdapter(adapter);
                }
            });
        }


    }

    private void initWight(View view) {
        recyclerView = view.findViewById(R.id.home_recycler);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductAdapter(Content.INSTANCE.getProducts(),getContext());
        recyclerView.setAdapter(adapter);
    }
}
