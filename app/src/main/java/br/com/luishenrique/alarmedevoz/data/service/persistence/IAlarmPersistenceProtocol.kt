package br.com.luishenrique.alarmedevoz.data.service.persistence

import br.com.luishenrique.alarmedevoz.data.entity.Alarm

interface IAlarmPersistenceProtocol {
    fun getAlarms(): List<Alarm>
    fun saveAlarm(value: Alarm)
    fun removeAlarm(value: Alarm)
}
