package com.se7sopro.advaegchallange.presenter.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.se7sopro.advaegchallange.R
import com.se7sopro.advaegchallange.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}