package com.laychv.gradleplugin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laychv.gradleplugin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var inflate: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)
    }
}