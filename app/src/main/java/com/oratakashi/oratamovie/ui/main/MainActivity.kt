package com.oratakashi.oratamovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.ActivityMainBinding
import com.oratakashi.viewbinding.core.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            val navController = findNavController(R.id.nav_host_fragment)
            btnNavMain.setupWithNavController(navController)
        }
    }

    private val binding : ActivityMainBinding by viewBinding()
}