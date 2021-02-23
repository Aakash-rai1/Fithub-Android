package com.aakash.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.WorkSource
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aakash.fithub.fragments.HomeFragment
import com.aakash.fithub.fragments.ProfileFragment
import com.aakash.fithub.fragments.WorkoutFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var linear: LinearLayout
    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    lateinit var toggleAction: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val workoutFragment = WorkoutFragment()
        val profileFragment = ProfileFragment()

        drawer=findViewById(R.id.drawer_layout);

       toggleAction = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close )
        drawer.addDrawerListener(toggleAction);
        toggleAction.syncState()



        linear =findViewById(R.id.linear)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);

        makeCurrentFragment(homeFragment)

        bottomNavigation= findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.icHome ->{makeCurrentFragment(homeFragment)
                    true
                }
                R.id.icWorkout ->{makeCurrentFragment(workoutFragment)
                    true
                }
                R.id.icProfile ->{makeCurrentFragment(profileFragment)
                    true
                }

                else -> false
            }
        }

        bottomNavigation.setOnNavigationItemReselectedListener {item->
            when(item.itemId){
                R.id.icHome ->{makeCurrentFragment(homeFragment)
                    true
                }
                R.id.icWorkout ->{makeCurrentFragment(workoutFragment)
                    true
                }
                R.id.icProfile ->{makeCurrentFragment(profileFragment)
                    true
                }
                else -> false
            }
        }


    }

    @Override
    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
           drawer.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
             setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}