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

    /*碎片管理者*/
    public static FragmentManager fragmentManager ;
    /*无登录时的产品列表*/
    public static List<ProductBean> products = new ArrayList<>();
    /*登录时的产品报价*/
    public static List<Double> prices = new ArrayList<>();
    /*实例一个Handler*/
    public static Handler handler = new Handler();
}
