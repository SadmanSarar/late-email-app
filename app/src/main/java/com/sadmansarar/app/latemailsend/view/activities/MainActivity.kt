package com.sadmansarar.app.latemailsend.view.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadmansarar.app.latemailsend.R
import com.sadmansarar.app.latemailsend.components.broadcastreciever.NotificationPublisherBroadcastReceiver
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import java.util.*
import javax.inject.Inject


/***
 * Activity that displays a list of Repos
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var prefRepository: PrefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (! prefRepository.getIsScheduled()) {
            scheduleNotification(1345)
        }

    }

    private fun scheduleNotification(notificationId: Int) {

        val notificationIntent = Intent(this, NotificationPublisherBroadcastReceiver::class.java)
        notificationIntent.putExtra(NotificationPublisherBroadcastReceiver.NOTIFICATION_ID, notificationId)
        notificationIntent.action = NotificationPublisherBroadcastReceiver.NOTIFICATION_ACTION
        val pendingIntent = PendingIntent.getBroadcast(this, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 10)
        calendar.set(Calendar.MINUTE, 15)
        calendar.set(Calendar.SECOND, 0)

        alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, (24 * 60 * 60 * 1000).toLong(), pendingIntent)

        prefRepository.saveIsScheduled(true)

    }

}
