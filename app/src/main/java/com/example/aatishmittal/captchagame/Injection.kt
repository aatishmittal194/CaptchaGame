package com.example.aatishmittal.captchagame

import com.example.aatishmittal.captchagame.data.CaptchaDataRepo
import com.example.aatishmittal.captchagame.data.FileDataSource
import com.example.android.architecture.blueprints.todoapp.util.AppExecutors

object Injection{
    fun provideCaptchaRepository() = CaptchaDataRepo(FileDataSource(AppExecutors(), CaptchaGame.getInstance()))
}