package com.example.myyyyapplication.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myyyyapplication.R
import com.example.myyyyapplication.databinding.ActivityMainBinding
import com.example.myyyyapplication.presentation.fragments.ClubsFragment
import com.example.myyyyapplication.presentation.fragments.MapFragment
import com.example.myyyyapplication.presentation.fragments.PlanFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.clubs -> replaceFragment(ClubsFragment())
                R.id.map -> replaceFragment(MapFragment())
                R.id.plan -> replaceFragment(PlanFragment())

                else -> {

                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.clubs
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}