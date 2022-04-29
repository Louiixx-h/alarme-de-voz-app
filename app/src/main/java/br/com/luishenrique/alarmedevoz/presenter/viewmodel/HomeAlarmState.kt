package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import br.com.luishenrique.alarmedevoz.data.entity.Alarm

sealed class HomeAlarmState {
    data class Success(val value: List<Alarm>) : HomeAlarmState()
    object GenericError : HomeAlarmState()
}
