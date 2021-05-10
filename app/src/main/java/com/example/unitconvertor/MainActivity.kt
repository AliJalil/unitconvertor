package com.example.unitconvertor

import com.example.unitconvertor.Unit
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unitconvertor.adapters.MetricAdapter
import com.example.unitconvertor.adapters.UnitSpinnerAdapter
import com.example.unitconvertor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var metricsAdapter: MetricAdapter? = null
    private var metrics = ArrayList<Metric>()
    lateinit var fromUnit: Unit
    lateinit var toUnit: Unit

    lateinit var fromTxt: EditText
    lateinit var toTxt: TextView
    lateinit var fromSpinner: Spinner
    lateinit var toSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        fromTxt = binding.fromTxt
        fromSpinner = binding.fromSpinner
        toSpinner = binding.toSpinner
        toTxt = binding.toTxt

        metrics = arrayListOf(
            Metric(
                "Height",
                arrayListOf(
                    Unit("Meter", 1.0),
                    Unit("cm", 0.01),
                    Unit("mlm", 0.001),
                    Unit("km", 1000.0)
                )
            ),
            Metric(
                "Weight",
                arrayListOf(
                    Unit("kilogram", 1.0),
                    Unit("gram", 0.01),
                    Unit("milligram", 0.001)
                )
            ),

            )


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.metricRec.layoutManager = layoutManager

        metricsAdapter = MetricAdapter(this, metrics) { metricItem ->
            setupSpinnerFrom(this, fromSpinner, metricItem.metricUnits)
            setupSpinnerTo(this, toSpinner, metricItem.metricUnits)

            fromUnit = metricItem.metricUnits[0]
            toUnit = metricItem.metricUnits[0]

            val x = fromTxt.text.toString().toDoubleOrNull() ?: 0.0
            toTxt.text = convert(x, fromUnit, toUnit).toString()
        }

        binding.metricRec.adapter = metricsAdapter
        metricsAdapter?.notifyDataSetChanged()


        fromTxt.addTextChangedListener {
            val x = fromTxt.text.toString().toDoubleOrNull() ?: 0.0
            toTxt.text = convert(x, fromUnit, toUnit).toString()
        }
    }


    private fun setupSpinnerFrom(
        context: Context,
        spinner: Spinner,
        pickerDataSource: ArrayList<Unit>
    ) {
        val pickerAdapter = UnitSpinnerAdapter(
            context,
            R.layout.item_rec,
            pickerDataSource
        )

        spinner.adapter = pickerAdapter

        //have the first item by-default selected
        spinner.setSelection(0)

        //Adding a listener to the custom spinner when an item is selected from the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                fromUnit = pickerDataSource[i]

                val x = fromTxt.text.toString().toDoubleOrNull() ?: 0.0
                toTxt.text = convert(x, fromUnit, toUnit).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                Toast.makeText(
                    context,
                    "Choose Unit",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupSpinnerTo(
        context: Context,
        spinner: Spinner,
        pickerDataSource: ArrayList<Unit>
    ) {
        val pickerAdapter = UnitSpinnerAdapter(
            context,
            R.layout.item_rec,
            pickerDataSource
        )

        spinner.adapter = pickerAdapter
        //have the first item by-default selected
        spinner.setSelection(0)

        //Adding a listener to the custom spinner when an item is selected from the spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                toUnit = pickerDataSource[i]

                val x = fromTxt.text.toString().toDoubleOrNull() ?: 0.0
                toTxt.text = convert(x, fromUnit, toUnit).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                Toast.makeText(
                    context,
                    "Choose Unit",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun convert(x: Double, from: Unit, to: Unit) = x * from.unitFactor / to.unitFactor

}