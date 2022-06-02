package br.com.luishenrique.alarmedevoz.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.entity.AlarmRequest
import br.com.luishenrique.alarmedevoz.data.entity.Date
import br.com.luishenrique.alarmedevoz.data.service.persistence.IAlarmPersistenceProtocol
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.AlarmCommand
import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.*
import org.junit.Assert.*
import org.junit.rules.TestRule

class AlarmRepositoryTest {

    @get:Rule val mockkRule = MockKRule(this)
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val alarmLiveData = MutableLiveData<AlarmCommand>()
    private val alarmPersistenceProtocol = spyk<IAlarmPersistenceProtocol>()

    private val alarmRepository: IAlarmRepository = AlarmRepository(
        alarmPersistenceProtocol,
        alarmLiveData
    )

    @Test
    fun `alarmLiveData should the same values as the alarmPersistenceProtocol getAlarms returns empty list`() {
        every {
            alarmPersistenceProtocol.getAlarms()
        } returns emptyList()

        alarmRepository.getAlarms()

        verify {
            alarmPersistenceProtocol.getAlarms()
            alarmLiveData.value
        }

        assertEquals(
            "Os valores não são iguais!",
            AlarmCommand.ListAlarms(emptyList()),
            alarmLiveData.value
        )
    }

    @Test
    fun `should save alarm if alarm is valid`() {
        val alarm = AlarmRequest(
            "good morning!",
            "Awake",
            R.color.black,
            "./",
            Date(1, 0)
        )

        val alarmCommand = alarmRepository.saveAlarm(alarm)

        verify {
            alarmRepository.alarmPersistenceProtocol.saveAlarm(alarm.toAlarm()!!)
            AlarmCommand.CreatedNewAlarm(alarm.toAlarm()!!)
        }

        assertEquals(
            "O alarme não foi salvo com sucesso!",
            AlarmCommand.CreatedNewAlarm(alarm.toAlarm()!!),
            alarmCommand
        )
    }
}