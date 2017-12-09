package com.example.lanceshu.bestvegetable.Utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Lance on 2017/12/9.
 */

public class GetGuest {

    public static void findGuest(Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("name","root")
                .add("pwd","123456").build();
        Request request = new Request.Builder()
                .url("http://123.207.145.251:8080/BestVegetable/guest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
