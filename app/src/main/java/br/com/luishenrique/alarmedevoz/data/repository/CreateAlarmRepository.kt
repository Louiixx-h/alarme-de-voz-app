package br.com.luishenrique.alarmedevoz.data.repository

import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.service.persistence.DataPersistenceConstants.ALARM_LIST
import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.CreateAlarmState
import com.orhanobut.hawk.Hawk

class CreateAlarmRepository(
    override val alarmPersistenceProtocol: IAlarmPersistenceProtocol
) : ICreateAlarmRepository {

    override fun saveAlarm(alarmRequest: AlarmRequest) : CreateAlarmState {
        return when {
            alarmRequest.title == null -> {
                CreateAlarmState.MissingTheTitle()
            }
            alarmRequest.sound == null -> {
                CreateAlarmState.MissingTheSound()
            }
            alarmRequest.date == null -> {
                CreateAlarmState.MissingTime
            }
            else -> {
                if (alarmRequest.toAlarm() == null) CreateAlarmState.GenericError
                else {
                    val alarm: Alarm = alarmRequest.toAlarm()!!
                    alarmPersistenceProtocol.saveAlarm(alarm)
                    CreateAlarmState.Success(alarm)
                }
            }
        }
    }
}