package com.example.aatishmittal.captchagame.data

interface DataSource{
    interface CaptchaListCallback{
        fun onSuccess(captchaList: List<Captcha>)
        fun onFailure(errorMsg : String)
    }

    fun getCaptchaList(callback : CaptchaListCallback)
}