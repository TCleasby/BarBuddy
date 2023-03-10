package com.example.barbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavCallbacks {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private var currentFragment: String = "Home"
    private val randomFragment = RandomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        navigationView.setNavigationItemSelectedListener(this)

        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // open starting fragment
        currentFragment = "Random Drink"
        toolbar.title = "Random Drink"
        supportFragmentManager.beginTransaction().apply { replace(R.id.fragment_container, randomFragment).commit() }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_random -> {
                Log.e("nav_random", "Clicked")
                currentFragment = "Random Drink"
                toolbar.title = "Random Drink"
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, RandomFragment()).commit()
            }
            R.id.nav_favorited -> {
                Log.i("nav_favorited", "Clicked")
                currentFragment = "Favorited Drinks"
                toolbar.title = "Favorited Drinks"
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavoritedDrinksFragment()).commit()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            when (currentFragment){
                "Random Drink" -> super.onBackPressed()
                "Favorited Drinks" -> super.onBackPressed()
                "Favorite Drink Details" -> {
                    currentFragment = "Favorited Drinks"
                    toolbar.title = "Favorited Drinks"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavoritedDrinksFragment()).commit()
                }
            }
        }
    }

    override fun onDrinkSelected(drink: FavoriteDrinkItem) {
        Log.i("MainActivity:", drink.instructions.toString())
        currentFragment = "Favorite Drink Details"
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SingleFavoritedDrinkFragment(drink)).commit()
    }
}