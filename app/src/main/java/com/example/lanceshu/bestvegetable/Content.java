package com.example.lanceshu.bestvegetable;

import android.os.Handler;
import android.support.v4.app.FragmentManager;

import com.example.lanceshu.bestvegetable.DataBean.ProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 2017/12/10.
 */

public class Content {

    public static FragmentManager fragmentManager ;
    public static List<ProductBean> products = new ArrayList<>();
    public static Handler handler = new Handler();
}
