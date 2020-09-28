package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.nav_length -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_weight -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_volume -> {
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                false
            }
        }
    }
}