package com.kaisha.flixstertrending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaisha.flixstertrending.databinding.ActivityMainBinding


private const val TAG = "MainActivity/"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // new way of setting up the layout instead of the traditional way of findingID
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.trendingContent, NowTrendingFragment(), null).commit()
    }
}