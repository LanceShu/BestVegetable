package com.example.lanceshu.bestvegetable.Utils;

import android.util.Log;

import com.example.lanceshu.bestvegetable.DataBean.GuestBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Lance on 2017/12/9.
 */

public class GetGuest {

    public static void findGuest(String name,String pass,Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("name",name)
                .add("pwd",pass)
                .build();
        Request request = new Request.Builder()
                .url("http://123.207.145.251:8080/BestVegetable/guest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static String getLoginState(String response){
        String state = null;

        try {
            JSONObject jsonObject = new JSONObject(response);
            state = jsonObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }

    public static void getGuestInfor(String respone){
        Log.e(" ",respone);
        try {
            JSONObject jsonObject = new JSONObject(respone);
            JSONObject guest = jsonObject.getJSONObject("guest");
            GuestBean.Companion.getInstance().setGid(guest.getString("id"));
            GuestBean.Companion.getInstance().setGpwd(guest.getString("pwd"));
            GuestBean.Companion.getInstance().setGname(guest.getString("name"));
            GuestBean.Companion.getInstance().setGaddr(guest.getString("addr"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
