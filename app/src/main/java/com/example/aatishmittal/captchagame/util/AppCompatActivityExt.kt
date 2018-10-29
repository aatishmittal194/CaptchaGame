package com.example.aatishmittal.captchagame.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ViewModelFactory

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass : Class<T>)
        = ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).commit()
}