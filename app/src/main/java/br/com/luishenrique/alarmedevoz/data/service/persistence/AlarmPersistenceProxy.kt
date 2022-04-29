package br.com.luishenrique.alarmedevoz.data.service.persistence

import br.com.luishenrique.alarmedevoz.data.entity.Alarm

class AlarmPersistenceProxy(
    private val alarmPersistenceProtocol: IAlarmPersistenceProtocol
): IAlarmPersistenceProtocol {

    override fun getAlarms(): List<Alarm> {
        return alarmPersistenceProtocol.getAlarms()
    }

    override fun saveAlarm(value: Alarm) {
        alarmPersistenceProtocol.saveAlarm(value)
    }
}