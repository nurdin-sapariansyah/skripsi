package com.masden.skripsi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masden.skripsi.api.ApiInterface
import com.masden.skripsi.api.ApiService
import com.masden.skripsi.data.quran.ModelAyat
import com.masden.skripsi.data.quran.ModelSurah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahViewModel : ViewModel() {
    private val modelSurahMutableLiveData = MutableLiveData<java.util.ArrayList<ModelSurah>>()
    private val modelAyatMutableLiveData = MutableLiveData<java.util.ArrayList<ModelAyat>>()

    fun setSurah() {
        val apiService: ApiInterface = ApiService.getQuran()
        val call = apiService.getListSurah()

        call.enqueue(object : Callback<java.util.ArrayList<ModelSurah>> {
            override fun onResponse(call: Call<java.util.ArrayList<ModelSurah>>, response: Response<java.util.ArrayList<ModelSurah>>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: java.util.ArrayList<ModelSurah> = ArrayList(response.body())
                    modelSurahMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<java.util.ArrayList<ModelSurah>>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun setDetailSurah(nomor: String) {
        val apiService: ApiInterface = ApiService.getQuran()
        val call = apiService.getDetailSurah(nomor)

        call.enqueue(object : Callback<java.util.ArrayList<ModelAyat>> {
            override fun onResponse(call: Call<java.util.ArrayList<ModelAyat>>, response: Response<java.util.ArrayList<ModelAyat>>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: java.util.ArrayList<ModelAyat> = ArrayList(response.body())
                    modelAyatMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<java.util.ArrayList<ModelAyat>>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun getSurah(): LiveData<java.util.ArrayList<ModelSurah>> = modelSurahMutableLiveData

    fun getDetailSurah(): LiveData<java.util.ArrayList<ModelAyat>> = modelAyatMutableLiveData
}