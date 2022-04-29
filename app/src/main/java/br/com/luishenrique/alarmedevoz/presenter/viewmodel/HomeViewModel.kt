package br.com.luishenrique.alarmedevoz.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.alarmedevoz.data.repository.IHomeAlarmRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val alarmHomeRepository: IHomeAlarmRepository
) : ViewModel(), IHomeViewModel {

    private val _state: MutableLiveData<HomeAlarmState> = MutableLiveData()
    override val state: LiveData<HomeAlarmState> = _state

    override fun getAlarms() {
        viewModelScope.launch {
            val response = alarmHomeRepository.getAlarms()
            response.run()
        }
    }

    private fun HomeAlarmState.run() {
        _state.postValue(this)
    }
}
