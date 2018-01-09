package com.example.lanceshu.bestvegetable.Fragemnet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lanceshu.bestvegetable.R;

/**
 * Created by Lance on 2018/1/9.
 */

public class ChandleryFragment extends Fragment{

    private View view;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.others_layout,container,false);
        initWight(view);
        return view;
    }

    private void initWight(View view) {
        textView = view.findViewById(R.id.other_layout);
        textView.setText("暂无杂货类信息");
    }
}
