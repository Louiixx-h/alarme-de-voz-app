package br.com.luishenrique.alarmedevoz.data.repository

import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.CreateAlarmState

interface ICreateAlarmRepository {
    val alarmPersistenceProtocol: IAlarmPersistenceProtocol
    fun saveAlarm(alarmRequest: AlarmRequest): CreateAlarmState
}
