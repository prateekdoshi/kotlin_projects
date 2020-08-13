package com.prateek.android.kotlin.live_data

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prateek.android.kotlin.BaseFragment
import com.prateek.android.kotlin.R
import kotlinx.android.synthetic.main.fragment_live_data.*

class LiveDataFragment : BaseFragment() {

    override fun getLayoutResource(): Int {
        return R.layout.fragment_live_data
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivityViewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        mainActivityViewModel.fetchCombinedName()

        clickme.setOnClickListener {
            mainActivityViewModel.fetchName(userId.text.toString().toInt())
            mainActivityViewModel.fetchNameWithMapTransformation(userId.text.toString().toInt())
            mainActivityViewModel.fetchNameWithSwitchMapTransformation(
                userId.text.toString().toInt()
            )

        }
        mainActivityViewModel.name.observe(this, Observer { name ->
            tx_name.text = "Name $name"
        })
        mainActivityViewModel.transformedMapName.observe(this, Observer { name ->
            tx_map_name.text = "mapName $name"
        })

        mainActivityViewModel.transformedSwitchMapName.observe(this, Observer { name ->
            tx_switchmap_name.text = "switchName $name"
        })

        mainActivityViewModel.mediatorLiveData.observe(this, Observer { combinedName ->
            tx_combined_name.text = "combinedName $combinedName"
        })

        mainActivityViewModel.transformedLiveDataBuilderName.observe(this, Observer { name ->
            tx_livedata_builder_name.text = "BuilderName $name"
        })
    }
}
