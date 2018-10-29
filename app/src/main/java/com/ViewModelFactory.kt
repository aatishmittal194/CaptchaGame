package com

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.example.aatishmittal.captchagame.Injection
import com.example.aatishmittal.captchagame.capcha.CaptchaGameViewModel
import com.example.aatishmittal.captchagame.capcha.CaptchaResultViewModel
import com.example.aatishmittal.captchagame.data.CaptchaDataRepo

/**
 * A creator is used to inject the product ID into the ViewModel
 *
 *
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */
class ViewModelFactory private constructor( private val captchaRepo: CaptchaDataRepo )
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(CaptchaGameViewModel::class.java) ->
                        CaptchaGameViewModel(captchaRepo)
                    isAssignableFrom(CaptchaResultViewModel::class.java) ->
                        CaptchaResultViewModel(captchaRepo)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(Injection.provideCaptchaRepository())
                            .also { INSTANCE = it }
                }

        @VisibleForTesting fun destroyInstance() {
            INSTANCE = null
        }
    }
}
