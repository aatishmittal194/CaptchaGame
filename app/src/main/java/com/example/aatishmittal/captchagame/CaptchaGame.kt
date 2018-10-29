package com.example.aatishmittal.captchagame

import android.app.Application

class CaptchaGame : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        private lateinit var instance: CaptchaGame

        fun getInstance() = instance
    }
}