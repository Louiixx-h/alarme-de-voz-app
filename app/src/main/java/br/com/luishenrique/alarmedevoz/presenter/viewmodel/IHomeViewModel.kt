package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData
import br.com.luishenrique.alarmedevoz.data.entity.Alarm

interface IHomeViewModel {
    val state: LiveData<AlarmCommand>
    fun getAlarms()
    fun removeAlarm(alarm: Alarm)
}
