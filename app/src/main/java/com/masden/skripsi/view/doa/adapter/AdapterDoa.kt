package com.masden.skripsi.view.doa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masden.skripsi.R
import com.masden.skripsi.data.doa.ModelDoa
import kotlinx.android.synthetic.main.list_item_doa.view.*

class AdapterDoa(private val modelBacaan: MutableList<ModelDoa>) :
    RecyclerView.Adapter<AdapterDoa.ListViewHolder>() {

    private val modelBacaanListFull: List<ModelDoa>

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
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_doa, viewGroup, false)
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