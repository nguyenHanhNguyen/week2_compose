package com.example.androiddevchallenge.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private var _countdown = MutableLiveData<Long>()
    val countdown: LiveData<Long> = _countdown

    fun updateCountdown(time: Long) {
        object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _countdown.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _countdown.value = 0
            }
        }.start()
    }
}