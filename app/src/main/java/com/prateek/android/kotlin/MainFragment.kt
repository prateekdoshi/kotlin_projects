package com.prateek.android.kotlin

import android.os.Bundle
import android.view.View
import com.prateek.android.kotlin.live_data.LiveDataFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(), View.OnClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        live_data.setOnClickListener(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_main
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.live_data -> navigateTo(LiveDataFragment())
        }
    }
}