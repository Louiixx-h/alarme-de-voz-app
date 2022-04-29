package br.com.luishenrique.alarmedevoz.data.config

import android.content.Context
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.service.persistence.DataPersistenceConstants.ALARM_LIST
import com.orhanobut.hawk.Hawk

object HawkConfig {

    fun init(context: Context) {
        Hawk.init(context).build()
        Hawk.put(ALARM_LIST, mutableListOf<Alarm>())
    }
}