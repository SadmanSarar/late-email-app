package com.sadmansarar.app.latemailsend.components.broadcastreciever

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sadmansarar.app.latemailsend.R
import com.sadmansarar.app.latemailsend.data.MailComposer
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import com.sadmansarar.app.latemailsend.view.activities.MainActivity


class NotificationPublisherBroadcastReceiver : BroadcastReceiver() {

    private lateinit var mPrefRepository: PrefRepository
    private lateinit var mMailComposer: MailComposer

    override fun onReceive(context: Context, intent: Intent) {

        mPrefRepository = PrefRepository(context)
        mMailComposer = MailComposer(mPrefRepository)

        val notificationId = intent.getIntExtra(NOTIFICATION_ID, 0)


        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        val sendMailPendingIntent = PendingIntent.getActivity(context, 123, mMailComposer.getLateMailIntent(), PendingIntent.FLAG_CANCEL_CURRENT)

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setContentTitle("Late for office?")
                .setContentText("Are you going to be late at the office?")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.ic_send_black_24dp, "Send Late Mail", sendMailPendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(NOTIFICATION_CHANNEL)
            val mChannel: NotificationChannel
            val importance = NotificationManager.IMPORTANCE_HIGH
            mChannel = NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_CHANNEL, importance)
            notificationManager?.createNotificationChannel(mChannel)
        }

        val contentIntent = Intent(context, MainActivity::class.java)
        val activity = PendingIntent.getActivity(context, notificationId, contentIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(activity)

        val notification = builder.build()

        notificationManager?.notify(notificationId, notification)
    }

    companion object {
        var NOTIFICATION_ID = "notification_id"
        const val NOTIFICATION_CHANNEL = "com.sadmansarar.app.something.view.broadcastreciever.NOTIFICATION_CHANNEL"
        var NOTIFICATION_ACTION = "NOTIFICATION_ACTION"
    }
}
