package com.os.operando.androidsupportlibrarysample

import android.os.Bundle
import android.support.design.chip.Chip
import android.support.v7.app.AppCompatActivity
import android.view.View
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).setOnClickListener {
            Timber.d("OnClick")
        }
        findViewById<Chip>(R.id.chip1).setOnCloseIconClickListener {
            Timber.d("OnClose")
        }
    }
}
