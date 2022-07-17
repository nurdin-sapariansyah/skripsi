package com.masden.skripsi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.masden.skripsi.R
import com.masden.skripsi.databinding.ActivityMainBinding
import com.masden.skripsi.view.fragment.HomeFragment
import com.masden.skripsi.view.fragment.NewsFragment
import com.masden.skripsi.view.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.news_info -> replaceFragment(NewsFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    //Memanggil fragment
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_fragment, fragment)
        fragmentTransaction.commit()
    }
}