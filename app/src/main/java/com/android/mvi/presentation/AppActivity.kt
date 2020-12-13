package com.android.mvi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.mvi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
