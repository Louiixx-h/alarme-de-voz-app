package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.alarmedevoz.data.repository.ICreateAlarmRepository
import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.entity.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CreateAlarmViewModel(
    private val createAlarmRepository: ICreateAlarmRepository
) : ViewModel(), ICreateAlarmViewModel {

    private val alarm: AlarmRequest = AlarmRequest()

    private val _state: MutableLiveData<CreateAlarmState> = MutableLiveData()
    override val state: LiveData<CreateAlarmState> = _state

    override fun setSound(sound: String) {
        alarm.sound = sound
    }

    override fun setColor(color: Int) {
        alarm.color = color
    }

    override fun setTitle(title: String) {
        alarm.title = title
    }

    override fun setDays() {
    }

    override fun saveAlarm() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = async { createAlarmRepository.saveAlarm(alarm) }
            _state.postValue(response.await())
        }
    }

    override fun setDate(hour: Int, minute: Int) {
        alarm.date = Date(hour, minute)
    }
}