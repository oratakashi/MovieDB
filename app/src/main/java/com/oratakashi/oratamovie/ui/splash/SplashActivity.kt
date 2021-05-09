package com.oratakashi.oratamovie.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oratakashi.oratamovie.databinding.ActivitySplashBinding
import com.oratakashi.oratamovie.ui.main.MainActivity
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.viewBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            lottie.addAnimatorUpdateListener {
                if(it.animatedFraction == 1.0f){
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }
        }
    }

    private val binding : ActivitySplashBinding by viewBinding()
}