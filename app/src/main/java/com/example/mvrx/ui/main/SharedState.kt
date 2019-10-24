package com.example.mvrx.ui.main

import android.content.Intent
import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import com.example.mvrx.R

class SharedState : BaseMvRxActivity(), FragmentChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_state)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout0, MainFragment.newInstance())
                .replace(R.id.frameLayout1, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onFragmentChange(key: String) {
        startActivity(Intent(this, UserProfileActivity::class.java))
    }
}
