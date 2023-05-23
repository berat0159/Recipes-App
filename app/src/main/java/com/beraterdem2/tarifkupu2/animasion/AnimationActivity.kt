package com.beraterdem2.tarifkupu2.animasion

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.beraterdem2.tarifkupu2.R

import com.beraterdem2.tarifkupu2.databinding.ActivityAnimationBinding
import com.beraterdem2.tarifkupu2.view.MainActivity

@Suppress("DEPRECATION")
class AnimationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationBinding
    private var splashScreen:Int=5000
    @SuppressLint("SuspiciousIndentation", "AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val imageAnim=AnimationUtils.loadAnimation(this,R.anim.image_anim)
        val tittleAnim=AnimationUtils.loadAnimation(this,R.anim.tittle_anim)
        val downAnim=AnimationUtils.loadAnimation(this,R.anim.down_anim)
        binding.imageView.animation=imageAnim
        binding.textView.animation=tittleAnim
        binding.textView2.animation=downAnim


        Handler().postDelayed({
                              val intent=Intent(this, MainActivity::class.java)
                                startActivity(intent)

        },splashScreen.toLong())
    }
}