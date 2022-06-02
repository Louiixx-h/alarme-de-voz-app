package br.com.luishenrique.alarmedevoz.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.AlarmCommand

class AlarmRepository(
    override val alarmPersistenceProtocol: IAlarmPersistenceProtocol,
    override val alarmLiveData: MutableLiveData<AlarmCommand>
) : IAlarmRepository {

    override fun getAlarms() {
        val alarms = alarmPersistenceProtocol.getAlarms() ?: emptyList<Alarm>()
        alarmLiveData.postValue(AlarmCommand.ListAlarms(alarms))
    }

    override fun saveAlarm(alarmRequest: AlarmRequest): AlarmCommand {
        return when {
            alarmRequest.title == null -> {
                AlarmCommand.MissingTheTitle()
            }
            alarmRequest.sound == null -> {
                AlarmCommand.MissingTheSound()
            }
            alarmRequest.date == null -> {
                AlarmCommand.MissingTime
            }
            else -> {
                if (alarmRequest.toAlarm() == null) AlarmCommand.GenericError
                else {
                    val alarm: Alarm = alarmRequest.toAlarm()!!
                    alarmPersistenceProtocol.saveAlarm(alarm)
                    getAlarms()
                    AlarmCommand.CreatedNewAlarm(alarm)
                }
            }
        }
    }

    override fun removeAlarm(alarm: Alarm): AlarmCommand {
        return try {
            alarmPersistenceProtocol.removeAlarm(alarm)
            getAlarms()
            AlarmCommand.DeleteAlarmSuccessful
        } catch (e: Exception) {
            AlarmCommand.GenericError
        }
    }
}