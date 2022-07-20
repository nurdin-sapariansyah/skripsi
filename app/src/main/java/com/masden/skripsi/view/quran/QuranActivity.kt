package com.masden.skripsi.view.quran

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.masden.skripsi.R
import com.masden.skripsi.data.quran.ModelSurah
import com.masden.skripsi.view.quran.adapter.SurahAdapter
import com.masden.skripsi.viewModel.SurahViewModel
import kotlinx.android.synthetic.main.activity_quran.*
import java.io.IOException
import java.util.*

class QuranActivity : AppCompatActivity() {

    lateinit var surahAdapter: SurahAdapter
    lateinit var progressDialog: ProgressDialog
    lateinit var surahViewModel: SurahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)
        setInitLayout()
        setViewModel()
    }

    private fun setInitLayout() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan data...")

        surahAdapter = SurahAdapter(this)
        rvSurah.setHasFixedSize(true)
        rvSurah.setLayoutManager(LinearLayoutManager(this))
        rvSurah.setAdapter(surahAdapter)
    }

    private fun setViewModel() {
        progressDialog.show()
        surahViewModel = ViewModelProvider(this, NewInstanceFactory()).get(SurahViewModel::class.java)
        surahViewModel.setSurah()
        surahViewModel.getSurah()
            .observe(this, { modelSurah: ArrayList<ModelSurah> ->
                if (modelSurah.size != 0) {
                    surahAdapter.setAdapter(modelSurah)
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(this, "Data Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
                progressDialog.dismiss()
            })
    }
}