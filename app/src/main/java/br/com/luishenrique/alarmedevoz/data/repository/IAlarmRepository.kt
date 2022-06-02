package br.com.luishenrique.alarmedevoz.data.repository

import androidx.lifecycle.LiveData
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.AlarmCommand

interface IAlarmRepository {
    val alarmPersistenceProtocol: IAlarmPersistenceProtocol
    val alarmLiveData: LiveData<AlarmCommand>
    fun saveAlarm(alarmRequest: AlarmRequest): AlarmCommand
    fun getAlarms()
    fun removeAlarm(alarm: Alarm): AlarmCommand
}
