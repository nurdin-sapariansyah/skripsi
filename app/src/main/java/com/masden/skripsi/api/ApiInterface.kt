package com.masden.skripsi.api

import com.masden.skripsi.data.quran.ModelAyat
import com.masden.skripsi.data.quran.ModelSurah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.ArrayList


interface ApiInterface {
    @GET("/99c279bb173a6e28359c/data")
    fun getListSurah(): Call<ArrayList<ModelSurah>>

    @GET("/99c279bb173a6e28359c/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: String
    ): Call<ArrayList<ModelAyat>>
}