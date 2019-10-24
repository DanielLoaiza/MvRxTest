package com.example.mvrx.ui.main

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import com.example.mvrx.R

class UserProfileActivity: BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserProfileFragment.newInstance("72"))
                .commitNow()
        }
    }
}