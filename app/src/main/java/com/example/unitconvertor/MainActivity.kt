package com.example.unitconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.unitconvertor.adapters.MetricAdapter
import com.example.unitconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var metricsAdapter: MetricAdapter? = null
    var metrics = ArrayList<Metric>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )


        val tempUnit0 = Unit("m",0.1)
        val tempUnit1 = Unit("cm",0.1)
        val tempUnit2 = Unit("mlm",0.01)

        val tempUnits = ArrayList<Unit>()
        tempUnits.add(tempUnit0)
        tempUnits.add(tempUnit1)
        tempUnits.add(tempUnit2)

         metrics.add( Metric(metricName = "Height", metricUnits = tempUnits))

        binding.metricRec.adapter  = metricsAdapter

        binding.button.setOnClickListener {
            binding.editTextTextPersonName.setText((binding.editTextTextPersonName.text.toString().toDouble() * metrics[0].metricUnits[0].unitFactor).toString())
        }

    }
}