package br.com.luishenrique.alarmedevoz.data.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.presenter.MainActivity

class AlarmNotification {

    private fun configAlarm(context: Context, alarm: Alarm) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
            .setContentTitle(alarm.title)
            .setContentText(alarm.description)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(alarm.description)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(CHANNEL_ID.toInt(), builder.build())
    }

    fun show(context: Context, alarm: Alarm) {
        createNotificationChannel(context)
        configAlarm(context, alarm)
    }

    companion object {
        const val CHANNEL_ID: String = "1003"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.channel_name)
                val descriptionText = context.getString(R.string.channel_name)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }

                val notificationManager: NotificationManager = context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}