package com.example.aatishmittal.captchagame.data

interface DataRepo{
    interface CaptchaCallback{
        fun onSuccess(captcha: Captcha)
        fun onFailure(errorMsg : String)
    }

    interface CaptchaResultCallback{
        fun onSuccess(captchaList: ArrayList<CaptchaResult>)
        fun onFailure(errorMsg : String)
    }

    fun getNextCaptcha(difficulty : Int,  callback: CaptchaCallback)
    fun saveCaptchaResult(captcha: Captcha, isCorrect : Boolean)
    fun getCaptchaResults(callback : CaptchaResultCallback)
    fun clear()
}