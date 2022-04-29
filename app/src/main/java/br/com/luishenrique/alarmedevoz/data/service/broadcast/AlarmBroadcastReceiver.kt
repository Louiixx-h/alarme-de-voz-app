package br.com.luishenrique.alarmedevoz.data.service.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.service.notification.AlarmNotification

class AlarmBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val alarm = intent?.getSerializableExtra("alarm") as Alarm? ?: return
        AlarmNotification().show(context, alarm)
    }
}