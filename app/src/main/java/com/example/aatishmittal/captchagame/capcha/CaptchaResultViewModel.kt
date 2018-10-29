package com.example.aatishmittal.captchagame.capcha

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.aatishmittal.captchagame.data.CaptchaResult
import com.example.aatishmittal.captchagame.data.DataRepo

class CaptchaResultViewModel(private val dataRepo: DataRepo) : ViewModel() {
    private var resultList : MutableLiveData<ArrayList<CaptchaResult>> = MutableLiveData()

    fun start()
    {
        dataRepo.getCaptchaResults(object : DataRepo.CaptchaResultCallback{
            override fun onFailure(errorMsg: String) {
            }

            override fun onSuccess(captchaList: ArrayList<CaptchaResult>) {
                resultList.value = captchaList
            }
        })
    }

    fun getResultList() :LiveData<ArrayList<CaptchaResult>> = resultList

    override fun onCleared() {
        super.onCleared()
        dataRepo.clear()
    }
}