package com.masden.skripsi.view.tasbih

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masden.skripsi.R
import kotlinx.android.synthetic.main.activity_tasbih.*

class TasbihActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R .layout.activity_tasbih)

        cv_hitung.setOnClickListener{
            text_zikir.text = (text_zikir.text.toString().toInt()+1).toString()
        }
        btn_reset.setOnClickListener{
            text_zikir.text = "0"
        }
    }
}