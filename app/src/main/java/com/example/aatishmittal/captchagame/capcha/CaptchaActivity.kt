package com.example.aatishmittal.captchagame.capcha

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aatishmittal.captchagame.R
import com.example.aatishmittal.captchagame.util.addFragmentToActivity
import com.example.aatishmittal.captchagame.util.obtainViewModel

class CaptchaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.captcha_activity)
        addFragmentToActivity(CaptchaGameFragment(), R.id.frame)
        setupResultScreenObserver()
    }

    private fun setupResultScreenObserver()
    {
        obtainViewModel().openResultScreen.observe(this, Observer {
            addFragmentToActivity(CaptchaResultFragment(), R.id.frame)
        })
    }
    fun obtainViewModel(): CaptchaGameViewModel = obtainViewModel(CaptchaGameViewModel::class.java)

    fun obtainResultViewModel(): CaptchaResultViewModel = obtainViewModel(CaptchaResultViewModel::class.java)

}