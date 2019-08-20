package com.sadmansarar.app.latemailsend.data

import android.content.Intent
import android.net.Uri
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import org.joda.time.DateTime


class MailComposer(private val mPrefRepository: PrefRepository) {

    fun getLateMailIntent(): Intent {
        val managerialGreetings = if (mPrefRepository.getManagerialGreetings().isNotEmpty()) mPrefRepository.getManagerialGreetings() else "Sir"
        val defaultLateMessage = if (mPrefRepository.getMailBody().isNotEmpty()) mPrefRepository.getMailBody() else "With regrets I am informing you that I won't be able to reach the office today within 10:20 AM today. I apologize for the inconvenience. I am prepared to make up for the delay if required."
        val yourName = if (mPrefRepository.getYourName().isNotEmpty()) mPrefRepository.getYourName() else ""
        val managerEmail = if (mPrefRepository.getManagerialEmail().isNotEmpty()) mPrefRepository.getManagerialEmail() else ""
        val hrEmail = if (mPrefRepository.getHrEmail().isNotEmpty()) mPrefRepository.getHrEmail() else ""

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.type = "text/html"
        intent.data = Uri.parse("mailto:$managerEmail")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(managerEmail))
        intent.putExtra(Intent.EXTRA_CC, arrayOf(hrEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Apology for delay on ${DateTime.now().toString("dd-MMMM-yyyy")}")

        intent.putExtra(Intent.EXTRA_TEXT, "" +
                "Dear $managerialGreetings,\n\n" +
                defaultLateMessage +
                "\n\n" +
                "Thanks and regards\n" +
                yourName)

        return Intent.createChooser(intent, "Send Late Email")
    }
}