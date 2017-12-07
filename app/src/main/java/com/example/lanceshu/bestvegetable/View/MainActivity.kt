package com.example.lanceshu.bestvegetable.View

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.lanceshu.bestvegetable.Content
import com.example.lanceshu.bestvegetable.Fragemnet.HomeFragment
import com.example.lanceshu.bestvegetable.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

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
                replaceFragment(HomeFragment())
            }
            R.id.nav_car -> {

            }
            R.id.nav_form -> {

            }
            R.id.nav_account -> {

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
