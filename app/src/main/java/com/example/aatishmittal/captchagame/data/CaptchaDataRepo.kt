package com.example.aatishmittal.captchagame.data

import java.util.*

class CaptchaDataRepo(private val dataSource : DataSource) : DataRepo {
    var captchaMap: MutableMap<Int, MutableList<Captcha>> = HashMap()
    var captchaResult: ArrayList<CaptchaResult> = ArrayList()

    override fun saveCaptchaResult(captcha: Captcha, isCorrect: Boolean) {
        captchaResult.add(CaptchaResult(isCorrect, captcha))
    }

    override fun getCaptchaResults(callback: DataRepo.CaptchaResultCallback) {
        callback.onSuccess(captchaResult)
    }

    override fun getNextCaptcha(difficulty: Int, callback: DataRepo.CaptchaCallback) {
        if (captchaMap.isEmpty()) {
            dataSource.getCaptchaList(object : DataSource.CaptchaListCallback{
                override fun onFailure(errorMsg: String) {
                    callback.onFailure(errorMsg)
                }

                override fun onSuccess(captchaList: List<Captcha>) {
                    saveListOfCaptcha(captchaList)
                    callback.onSuccess(giveRandomCaptcha(difficulty))
                }

            })
        }else {
            callback.onSuccess(giveRandomCaptcha(difficulty))
        }

    }

    fun giveRandomCaptcha(difficulty : Int) : Captcha
    {
        val list = captchaMap.get(difficulty)
        val size = list!!.size
        return list.removeAt(Random().nextInt(size-1))
    }

    private fun saveListOfCaptcha(list : List<Captcha>)
    {
       list.forEach{
            if(captchaMap.containsKey(it.difficulty)) {
                captchaMap.get(it.difficulty)!!.add(it)
            }else {
                val list = emptyList<Captcha>().toMutableList()
                list.add(it)
                captchaMap.put(it.difficulty, list)
            }
        }
    }

    override fun clear() {
        captchaMap.clear()
        captchaResult.clear()
    }
}