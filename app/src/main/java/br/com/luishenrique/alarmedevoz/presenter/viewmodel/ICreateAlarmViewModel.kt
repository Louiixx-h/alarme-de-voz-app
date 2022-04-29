package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData

interface ICreateAlarmViewModel {

    val state: LiveData<CreateAlarmState>

    fun setSound(sound: String)
    fun setColor(color: Int)
    fun setTitle(title: String)
    fun setDays()
    fun saveAlarm()
    fun setDate(hour: Int, minute: Int)
}
