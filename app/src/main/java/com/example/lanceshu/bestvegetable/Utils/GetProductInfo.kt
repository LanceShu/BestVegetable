package com.example.lanceshu.bestvegetable.Utils

import android.util.Log
import com.example.lanceshu.bestvegetable.DataBean.ProductBean
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by lanceshu on 17-12-7.
 */
object GetProductInfo {

    fun getInfo(callback: Callback){

        val client = OkHttpClient()
        val request = Request.Builder()
                .url("http://123.207.145.251:8080/BestVegetable/product")
                .build()
        client.newCall(request).enqueue(callback)
    }

    fun handleProductInfo(resp : String) : List<ProductBean>{

        var products = ArrayList<ProductBean>()

        val jsonArray = JSONArray(resp)
        for(i in 0 until jsonArray.length()){
            val jsonObeject = jsonArray.getJSONObject(i)
            var product = ProductBean()
            product.pId = jsonObeject.getString("id")
            product.pUnit = jsonObeject.getString("unit")
            product.pCode = jsonObeject.getString("p_code")
            product.pName = jsonObeject.getString("name")
            product.pPrice = jsonObeject.getDouble("price")
            product.pImagfile = jsonObeject.getString("imagfile")
            product.pNote = jsonObeject.getString("note")
//            Log.e("jsonObject",product.pName+","+product.pImagfile)
            products.add(product)
        }

        return products

    }

}