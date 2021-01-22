package com.aakash.fithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.WorkSource
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.aakash.fithub.fragments.HomeFragment
import com.aakash.fithub.fragments.ProfileFragment
import com.aakash.fithub.fragments.WorkoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    lateinit var linear: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment =HomeFragment()
        val workoutFragment = WorkoutFragment()
        val profileFragment = ProfileFragment()

        linear =findViewById(R.id.linear)

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

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.linear, fragment)
            // setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }
}