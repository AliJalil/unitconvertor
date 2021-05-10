package com.example.unitconvertor.adapters

import com.example.unitconvertor.R
import com.example.unitconvertor.Unit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.ArrayList


class UnitSpinnerAdapter(context: Context, resource: Int, pickerItems: ArrayList<Unit>) :
    ArrayAdapter<Unit>(context, resource, pickerItems) {
    private var unitSpinnerItems: ArrayList<Unit> = pickerItems

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return CustomSpinnerView(position, convertView, parent)
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        return CustomSpinnerView(position, convertView, parent)
    }

    private fun CustomSpinnerView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Getting the Layout Inflater Service from the system
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //Inflating out custom spinner view
        val customView = layoutInflater.inflate(R.layout.item_rec, parent, false)
        val textView = customView.findViewById(R.id.metricName) as TextView

        textView.text = unitSpinnerItems[position].unitName

        return customView
    }

}