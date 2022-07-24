package com.masden.skripsi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.masden.skripsi.R
import com.masden.skripsi.databinding.ActivityMainBinding
import com.masden.skripsi.view.doa.DoaActivity
import com.masden.skripsi.view.profile.JadwalFragment.Companion.newInstance
import com.masden.skripsi.view.profile.ProfileActivity
import com.masden.skripsi.view.quran.QuranActivity
import com.masden.skripsi.view.tasbih.TasbihActivity
import com.masden.skripsi.view.zikir.ZikirActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardview1 : CardView = findViewById(R.id.quran_menu)
        val cardview2 : CardView = findViewById(R.id.doa_menu)
        val cardview3 : CardView = findViewById(R.id.tasbih_menu)
        val cardview4 : CardView = findViewById(R.id.zikir_menu)
        val cardview5 : CardView = findViewById(R.id.profile_menu)

        cardview1.setOnClickListener{
            var i = Intent(this, QuranActivity::class.java)
            startActivity(i)
        }
        cardview2.setOnClickListener{
            var i = Intent(this, DoaActivity::class.java)
            startActivity(i)
        }
        cardview3.setOnClickListener{
            var i = Intent(this, TasbihActivity::class.java)
            startActivity(i)
        }
        cardview4.setOnClickListener{
            var i = Intent(this, ZikirActivity::class.java)
            startActivity(i)
        }
        cardview5.setOnClickListener{
            var i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }

        val jadwalSholat = newInstance("Jadwal Sholat")
        layoutTime.setOnClickListener {
            jadwalSholat.show(
                supportFragmentManager, jadwalSholat.tag
            )
        }
    }

}