package com.example.ganesh.navdrawer

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Realm.init(this)
        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

         fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction.func()
            fragmentTransaction.commit()
        }

        fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
            supportFragmentManager.inTransaction { add(frameId, fragment) }
        }


        fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
            supportFragmentManager.inTransaction{replace(frameId, fragment)}
        }
      var fragment : Fragment? = null;


        when (item.itemId) {
            R.id.nav_ride -> {
                fragment = AddRide()

            }
            R.id.nav_viewrides -> {
                fragment = ViewRides()
            }
            R.id.nav_map -> {
                fragment = MapFragment()
            }

            R.id.nav_manage -> {

            }

            R.id.nav_send -> {

            }
        }
          if (fragment != null)
          {
              //mFragmentStack = Stack()

              supportFragmentManager.inTransaction {
                  replace(R.id.screen_area, fragment)
              }


          }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
