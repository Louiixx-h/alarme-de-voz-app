package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import br.com.luishenrique.alarmedevoz.data.entity.Alarm

sealed class CreateAlarmState {
    data class Success(val value: Alarm) : CreateAlarmState()
    data class MissingTheTitle(val error: Exception = Exception()) : CreateAlarmState()
    data class MissingTheSound(val error: Exception = Exception()) : CreateAlarmState()
    data class MissingRepeatDay(val error: Exception = Exception()) : CreateAlarmState()
    object MissingTime : CreateAlarmState()
    object GenericError : CreateAlarmState()
}