package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData

interface IHomeViewModel {
    val state: LiveData<HomeAlarmState>
    fun getAlarms()
}
