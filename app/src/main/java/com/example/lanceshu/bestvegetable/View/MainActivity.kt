package com.example.lanceshu.bestvegetable.View

import android.app.Dialog
import android.os.Bundle
import android.os.Message
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lanceshu.bestvegetable.Content
import com.example.lanceshu.bestvegetable.DataBean.GuestBean
import com.example.lanceshu.bestvegetable.Fragemnet.*
import com.example.lanceshu.bestvegetable.R
import com.example.lanceshu.bestvegetable.Utils.GetGuest
import com.example.lanceshu.bestvegetable.Utils.GetProductInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initData()
        initWight()

    }

    /**
     * 初始化数据;
     * */
    fun initData(){

        if (Content.products.size == 0) {
            GetProductInfo.getInfo(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    Content.products = GetProductInfo.handleProductInfo(response.body().string())
                    var message = Message.obtain()
                    message.what = 1
                    Content.handler.sendMessage(message)
                }
            })
        }

        Content.productBeans.clear()
        Content.isLogin = false

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.type_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.vegetable -> {
                replaceFragment(HomeFragment())
            }
            R.id.meat -> {
                replaceFragment(MeatFragment())
            }
            R.id.fish -> {
                replaceFragment(FishFragment())
            }
            R.id.drycargo -> {
                replaceFragment(DrycargoFragment())
            }
            R.id.chandlery -> {
                replaceFragment(ChandleryFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     *初始化控件;
     * */
    fun initWight(){

        toolbar.setTitle("优菜网")

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        replaceFragment(HomeFragment())

        val headerView = nav_view.getHeaderView(0)
        val tlogin : TextView = headerView.findViewById(R.id.isLogin)
        tlogin.setOnClickListener {
            val dialog = Dialog(this@MainActivity,R.style.DialogTheme)
            dialog.setContentView(R.layout.login_layout)
            val name : EditText = dialog.findViewById(R.id.name)
            val pass : EditText = dialog.findViewById(R.id.pass)
            val login : Button = dialog.findViewById(R.id.login)
            login.setOnClickListener {
                if(name.text.toString() != "" && pass.text.toString() != "" && GuestBean.getInstance() == null){
                    GetGuest.findGuest(name.text.toString(),pass.text.toString(),object : Callback {
                        override fun onFailure(call: Call?, e: IOException?) {
                            Log.e("error",e.toString())
                        }

                        override fun onResponse(call: Call?, response: Response?) {
                            val resp = response!!.body().string()
                            val status = GetGuest.getLoginState(resp)
                            Log.e("status",status)
                            if(status == "success"){
                                Content.isLogin = true
                                GetGuest.getGuestInfor(resp)
                                /*获取用户的报价单*/
                                GetGuest.getUserPrice()
                                runOnUiThread {
                                    dialog.dismiss()
                                    Toast.makeText(this@MainActivity,"登录成功,"+GuestBean.getInstance().gname,Toast.LENGTH_SHORT).show()
                                    tlogin.text = GuestBean.getInstance().gname + "  " + GuestBean.getInstance().gaddr
                                    replaceFragment(HomeFragment())
                                }
                            }else{
                                runOnUiThread {
                                    Toast.makeText(this@MainActivity,"账号密码不匹配~",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }else if(GuestBean.getInstance() != null){
                    /*令用户变为null*/
                    GuestBean.clearInstance()
                    GetGuest.findGuest(name.text.toString(),pass.text.toString(),object : Callback {
                        override fun onFailure(call: Call?, e: IOException?) {
                            Log.e("error",e.toString())
                        }

                        override fun onResponse(call: Call?, response: Response?) {
                            val resp = response!!.body().string()
                            val status = GetGuest.getLoginState(resp)
                            Log.e("status",status)
                            if(status == "success"){
                                Content.isLogin = true
                                GetGuest.getGuestInfor(resp)
                                /*获取用户的报价单*/
                                GetGuest.getUserPrice()
                                runOnUiThread {
                                    dialog.dismiss()
                                    Toast.makeText(this@MainActivity,"登录成功,"+GuestBean.getInstance().gname,Toast.LENGTH_SHORT).show()
                                    tlogin.text = GuestBean.getInstance().gname + "  " + GuestBean.getInstance().gaddr
                                    replaceFragment(HomeFragment())
                                }
                            }else{
                                runOnUiThread {
                                    Toast.makeText(this@MainActivity,"账号密码不匹配~",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                    })
                } else{
                    Toast.makeText(this@MainActivity,"账号密码不能为空~~",Toast.LENGTH_SHORT).show()
                }
            }
            dialog.show()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_all -> {
                toolbar.setTitle("优菜网")
                replaceFragment(HomeFragment())
            }
            R.id.nav_form -> {
                toolbar.setTitle("我的订单")
                replaceFragment(OrderFragment())
            }
            R.id.nav_account -> {
                toolbar.setTitle("个人设置")

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragment(fragment: Fragment){
        Content.fragmentManager = supportFragmentManager
        val transaction = Content.fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragment,fragment)
        transaction.commit()
    }
}
