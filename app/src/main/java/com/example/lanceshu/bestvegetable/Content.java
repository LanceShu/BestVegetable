package com.example.lanceshu.bestvegetable;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import com.example.lanceshu.bestvegetable.DataBase.MyDataBaseHelper;
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
    /*下单的列表*/
    public static List<ProductBean> productBeans = new ArrayList<>();
    /*登录状态*/
    public static boolean isLogin = false;

    public static MyDataBaseHelper dataBaseHelper;
    public static SQLiteDatabase database;

    public static String userName;
}
