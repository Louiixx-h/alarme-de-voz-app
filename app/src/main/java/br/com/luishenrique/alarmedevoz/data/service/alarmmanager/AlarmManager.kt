package br.com.luishenrique.alarmedevoz.data.service.alarmmanager

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.data.entity.Date
import br.com.luishenrique.alarmedevoz.data.service.broadcast.AlarmBroadcastReceiver
import java.util.*
import java.util.concurrent.TimeUnit
import android.app.AlarmManager as ServiceAlarmManager

object AlarmManager {
    private const val milliSeconds: Long = 1000000

    @SuppressLint("ServiceCast")
    fun create(context: Context, alarm: Alarm) {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java).apply {
            action = context.getString(R.string.intent_action_alarm)
            putExtra("alarm", alarm)
        }
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? ServiceAlarmManager
        val calendar = Calendar.getInstance()
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR, alarm.date.hour)
        calendar.set(Calendar.SECOND, alarm.date.minute)

        if (pendingIntent == null || alarmManager == null) return

        alarmManager.setInexactRepeating(
            android.app.AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000*60*1,
            pendingIntent
        )
    }
}