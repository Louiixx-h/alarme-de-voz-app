package br.com.luishenrique.alarmedevoz.data.repository

import br.com.luishenrique.alarmedevoz.presenter.viewmodel.HomeAlarmState

interface IHomeAlarmRepository {
    suspend fun getAlarms(): HomeAlarmState
}
