package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentValues) as ValuesFragment
        when(item.itemId) {
            R.id.nav_length -> {
                fragment.changeList(R.array.length)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_weight -> {
                fragment.changeList(R.array.weight)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_volume -> {
                fragment.changeList(R.array.volume)
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                false
            }
        }
    }
}