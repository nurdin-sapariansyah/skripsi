package com.masden.skripsi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.masden.skripsi.R
import com.masden.skripsi.databinding.ActivityMainBinding
import com.masden.skripsi.view.fragment.HomeFragment
import com.masden.skripsi.view.fragment.NewsFragment
import com.masden.skripsi.view.fragment.ProfileFragment
import com.masden.skripsi.view.quran.QuranActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        val cardview1 : CardView = findViewById(R.id.quran_menu)
        val cardview2 : CardView = findViewById(R.id.doa_menu)
        cardview1.setOnClickListener{
            var i = Intent(this, QuranActivity::class.java)
            startActivity(i)
        }
        cardview2.setOnClickListener{
            var i = Intent(this, DoaActivity::class.java)
            startActivity(i)
        }

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