package com.example.unitconvertor.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unitconvertor.Metric
import com.example.unitconvertor.R
import java.util.ArrayList


class MetricAdapter internal constructor(
    internal var context: Context,
    arrayList: ArrayList<Metric>,
    val itemClick: (Metric) -> Unit
) :
    RecyclerView.Adapter<MetricAdapter.ViewHolder>() {

    private var arrayList: ArrayList<Metric>
    init {
        this.arrayList = arrayList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rec, parent, false)
        return ViewHolder(view, itemClick)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(arrayList[position])
    }
    override fun getItemCount(): Int {
        return arrayList.size
    }
    inner class ViewHolder(itemView: View, val itemClick: (Metric) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        var name = itemView.findViewById<TextView>(R.id.metricName)

        fun bindViewHolder(generalItem: Metric) {
            name.text = generalItem.metricName
            itemView.setOnClickListener {
                itemClick(generalItem)
            }
        }
    }
}