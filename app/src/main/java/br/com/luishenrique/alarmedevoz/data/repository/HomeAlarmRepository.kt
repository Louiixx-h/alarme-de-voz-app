package br.com.luishenrique.alarmedevoz.data.repository

import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.HomeAlarmState

class HomeAlarmRepository(
    private val alarmPersistenceProtocol: IAlarmPersistenceProtocol
): IHomeAlarmRepository {

    override suspend fun getAlarms(): HomeAlarmState {
        return try {
            val alarms = alarmPersistenceProtocol.getAlarms()
            HomeAlarmState.Success(alarms)
        } catch (e: Exception) {
            HomeAlarmState.GenericError
        }
    }
}
