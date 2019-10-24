package com.example.mvrx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.mvrx.BaseMvRxActivity
import com.example.mvrx.ui.main.FragmentChangeListener
import com.example.mvrx.ui.main.MainFragment
import com.example.mvrx.ui.main.SharedState
import com.example.mvrx.ui.main.TemperatureFragment

class MainActivity : BaseMvRxActivity(), FragmentChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onFragmentChange(key: String) {

        when (key) {
            "temperature" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TemperatureFragment.newInstance())
                    .commitNow()
            }
            else -> {
                startActivity(Intent(this, SharedState::class.java))
            }
        }

    }
}
