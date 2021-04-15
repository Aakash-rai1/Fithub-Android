package com.aakash.fithub.ui


import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aakash.fithub.R
import com.aakash.fithub.entity.Workout
import com.aakash.fithub.fragments.*

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var doubleBackToExitPressedOnce = false
    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var linear: LinearLayout
    private lateinit var drawer: DrawerLayout
    lateinit var toggleAction: ActionBarDrawerToggle
    private lateinit var navView: NavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayList=ArrayList<Workout>()

        arrayList.add(Workout())

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val favFragment = FavorateFragment()
        val bmiFragment= BmiFragment()
        val MapsFragment= MapsFragment()


        drawer=findViewById(R.id.drawer_layout);

       toggleAction = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggleAction);
        toggleAction.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        linear =findViewById(R.id.linear)

        navView= findViewById(R.id.navView)



        //fragments from slider menu
        navView.setNavigationItemSelectedListener(this)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.maps ->{makeCurrentFragment(MapsFragment)}
            }
            when(it.itemId){
                R.id.bmi ->{makeCurrentFragment(bmiFragment)}
            }



            true
        }



        //opening fragments from bottom navigation
        makeCurrentFragment(homeFragment)

        bottomNavigation= findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.icHome ->{makeCurrentFragment(homeFragment)
                    true
                }

                R.id.icProfile ->{makeCurrentFragment(profileFragment)
                    true
                }
                R.id.fav ->{makeCurrentFragment(favFragment)
                    true
                }



                else -> false
            }
        }




    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggleAction.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    @Override
    override fun onBackPressed() {
//        if(drawer.isDrawerOpen(GravityCompat.START)){
//           drawer.closeDrawer(GravityCompat.START)
//        }else {
//            super.onBackPressed()
//        }

//double back press to exit
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
             setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(toggleAction.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item)

    }



}