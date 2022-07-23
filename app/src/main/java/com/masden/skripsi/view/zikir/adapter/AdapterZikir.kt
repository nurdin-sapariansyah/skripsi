package com.masden.skripsi.view.zikir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masden.skripsi.R
import com.masden.skripsi.data.zikir.ModelZikir
import kotlinx.android.synthetic.main.list_item_zikir.view.*
import kotlinx.android.synthetic.main.list_item_zikir.view.tvArabic
import kotlinx.android.synthetic.main.list_item_zikir.view.tvId
import kotlinx.android.synthetic.main.list_item_zikir.view.tvLatin
import kotlinx.android.synthetic.main.list_item_zikir.view.tvTerjemahan
import java.util.*
import kotlin.collections.ArrayList

class AdapterZikir(private val modelBacaan: MutableList<ModelZikir>) :
    RecyclerView.Adapter<AdapterZikir.ListViewHolder>() {

    private val modelBacaanListFull: List<ModelZikir>

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView
        var tvTitle: TextView
        var tvArabic: TextView
        var tvLatin: TextView
        var tvTerjemahan: TextView

        init {
            tvId = itemView.tvId
            tvTitle = itemView.tvTitle
            tvArabic = itemView.tvArabic
            tvLatin = itemView.tvLatin
            tvTerjemahan = itemView.tvTerjemahan
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_zikir, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.tvId.text = dataModel.strId
        listViewHolder.tvTitle.text = dataModel.strTitle
        listViewHolder.tvArabic.text = dataModel.strArabic
        listViewHolder.tvLatin.text = dataModel.strLatin
        listViewHolder.tvTerjemahan.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }
}