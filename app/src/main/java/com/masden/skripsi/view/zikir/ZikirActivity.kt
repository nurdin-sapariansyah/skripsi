package com.masden.skripsi.view.zikir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.masden.skripsi.R
import com.masden.skripsi.data.zikir.ModelZikir
import com.masden.skripsi.view.zikir.adapter.AdapterZikir
import kotlinx.android.synthetic.main.activity_zikir.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.ArrayList

class ZikirActivity : AppCompatActivity() {

    var adapterZikir: AdapterZikir? = null
    var modelZikir: MutableList<ModelZikir> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir)

        rvListZikir.setLayoutManager(LinearLayoutManager(this))
        rvListZikir.setHasFixedSize(true)

        //get data
        getZikir()
    }

    private fun getZikir() {
        try {
            val stream = assets.open("zikir.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strResponse = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strResponse)
                val jsonArray = jsonObject.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val dataModel = ModelZikir()
                    dataModel.strId = jsonObjectData.getString("id")
                    dataModel.strTitle = jsonObjectData.getString("title")
                    dataModel.strArabic = jsonObjectData.getString("arabic")
                    dataModel.strLatin = jsonObjectData.getString("latin")
                    dataModel.strTranslation = jsonObjectData.getString("translation")
                    modelZikir.add(dataModel)
                }
                adapterZikir = AdapterZikir(modelZikir)
                rvListZikir.adapter = adapterZikir
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
        }
    }

}