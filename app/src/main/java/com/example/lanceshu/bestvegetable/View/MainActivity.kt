package com.example.lanceshu.bestvegetable.View

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.lanceshu.bestvegetable.Content
import com.example.lanceshu.bestvegetable.Fragemnet.HomeFragment
import com.example.lanceshu.bestvegetable.R
import com.example.lanceshu.bestvegetable.Utils.GetGuest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initData()
        initWight()

    }

    /**
     * 初始化数据;
     * */
    fun initData(){

        GetGuest.findGuest(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("error",e.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("response",response!!.body().string())
            }

        })
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
            R.id.nav_car -> {
                toolbar.setTitle("购物车")

            }
            R.id.nav_form -> {
                toolbar.setTitle("我的订单")

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
