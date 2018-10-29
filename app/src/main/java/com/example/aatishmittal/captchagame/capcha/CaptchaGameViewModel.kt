package com.example.aatishmittal.captchagame.capcha

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.CountDownTimer
import com.example.aatishmittal.captchagame.SingleLiveEvent
import com.example.aatishmittal.captchagame.data.Captcha
import com.example.aatishmittal.captchagame.data.DataRepo
import com.example.aatishmittal.captchagame.data.State
import com.example.aatishmittal.captchagame.util.Constants.Companion.AVERAGE_DIFFICULTY
import com.example.aatishmittal.captchagame.util.Constants.Companion.MAX_CAPTCHA_NUMBER
import com.example.aatishmittal.captchagame.util.Constants.Companion.MAX_DIFFICULTY
import com.example.aatishmittal.captchagame.util.Constants.Companion.MAX_TIME_IN_SEC
import com.example.aatishmittal.captchagame.util.Constants.Companion.MIN_DIFFICULTY

class CaptchaGameViewModel(private val dataRepo: DataRepo) : ViewModel() {
    private var currentCaptchaNumber = 1
    private var currentDifficulty = AVERAGE_DIFFICULTY
    var currentCaptcha: MutableLiveData<Captcha> = MutableLiveData()
    var currentTimer: MutableLiveData<Int> = MutableLiveData()
    val uiState: MutableLiveData<State> = MutableLiveData()
    val openResultScreen : SingleLiveEvent<Void> = SingleLiveEvent()
    private lateinit var countDownTimer: CountDownTimer

    fun start() {
        setUiState(State.LOADING)
        loadNextCaptcha(true)
    }

    private fun loadNextCaptcha(isFirstCaptcha: Boolean) {
        dataRepo.getNextCaptcha(currentDifficulty, object : DataRepo.CaptchaCallback {
            override fun onSuccess(captcha: Captcha) {
                currentCaptcha.value = captcha
                if (isFirstCaptcha)
                    setUiState(State.QUIZ)
                startTimer()
            }
            override fun onFailure(errorMsg: String) {
            }
        })
    }


    private fun startTimer() {
        currentTimer.value = MAX_TIME_IN_SEC

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTimer.value = currentTimer.value!!.minus(1)
            }
            override fun onFinish() {
                saveCaptchaResultAndChangeCurrentDifficulty("")
                postCaptchaCalculation()
                currentTimer.value = MAX_TIME_IN_SEC
            }
        }
        countDownTimer.start()
    }

    private fun postCaptchaCalculation() {
        if (currentCaptchaNumber < MAX_CAPTCHA_NUMBER) {
            currentCaptchaNumber++
            loadNextCaptcha(false)
        } else {
            setUiState(State.RESULT)
        }
    }

    private fun stopTimer() {
        countDownTimer.cancel()
    }

    fun setUiState(state: State) {
        uiState.value = state
    }

    fun submit(answer : String) {
        saveCaptchaResultAndChangeCurrentDifficulty(answer)
        stopTimer()
        postCaptchaCalculation()
    }

    private fun saveCaptchaResultAndChangeCurrentDifficulty(answer: String)
    {
        val isCorrectAnswer = currentCaptcha.value!!.answer == answer
        saveCaptchaResult(isCorrectAnswer)
        changeCurrentDifficulty(isCorrectAnswer)
    }

    private fun saveCaptchaResult(isCorrectAnswer : Boolean)
    {
        dataRepo.saveCaptchaResult(currentCaptcha.value!!, isCorrectAnswer)
    }

    private fun changeCurrentDifficulty(isCorrectAnswer: Boolean)
    {
        if(isCorrectAnswer){
            if(currentDifficulty < MAX_DIFFICULTY)
                currentDifficulty++
        }else
        {
            if(currentDifficulty > MIN_DIFFICULTY)
                currentDifficulty--
        }
    }
}