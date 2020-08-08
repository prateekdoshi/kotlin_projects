package com.prateek.android.kotlin_livedata

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        clickme.setOnClickListener {
            mainActivityViewModel.fetchName(id.text.toString().toInt())
            mainActivityViewModel.fetchNameWithMapTransformation(id.text.toString().toInt())
            mainActivityViewModel.fetchNameWithSwitchMapTransformation(id.text.toString().toInt())

        }
        mainActivityViewModel.name.observe(this, Observer { name ->
            println("actiivty ${System.currentTimeMillis()}")
            tx_name.text = name
        })
        mainActivityViewModel.transformedMapName.observe(this, Observer { name ->
            println("actiivty ${System.currentTimeMillis()}")
            tx_name.text = name
        })

        mainActivityViewModel.transformedSwitchMapName.observe(this, Observer { name ->
            println("actiivty ${System.currentTimeMillis()}")
            tx_name.text = name
        })
    }
}
