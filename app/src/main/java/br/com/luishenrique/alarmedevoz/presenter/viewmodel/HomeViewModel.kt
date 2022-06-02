package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.repository.IAlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val alarmHomeRepository: IAlarmRepository
) : ViewModel(), IHomeViewModel {

    override val state: LiveData<AlarmCommand> = alarmHomeRepository.alarmLiveData

    override fun getAlarms() {
        alarmHomeRepository.getAlarms()
    }

    override fun removeAlarm(alarm: Alarm) {
        alarmHomeRepository.removeAlarm(alarm)
    }
}
