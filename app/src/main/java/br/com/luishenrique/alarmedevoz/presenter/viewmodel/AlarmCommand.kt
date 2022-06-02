package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import br.com.luishenrique.alarmedevoz.data.entity.Alarm

sealed class AlarmCommand {
    data class ListAlarms(val value: List<Alarm>) : AlarmCommand()
    data class CreatedNewAlarm(val value: Alarm) : AlarmCommand()
    data class MissingTheTitle(val error: Exception = Exception()) : AlarmCommand()
    data class MissingTheSound(val error: Exception = Exception()) : AlarmCommand()
    data class MissingRepeatDay(val error: Exception = Exception()) : AlarmCommand()

    object MissingTime : AlarmCommand()
    object GenericError : AlarmCommand()
    object DeleteAlarmSuccessful : AlarmCommand()
}