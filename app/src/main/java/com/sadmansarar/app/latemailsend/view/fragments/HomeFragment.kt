package com.sadmansarar.app.latemailsend.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sadmansarar.app.latemailsend.R
import com.sadmansarar.app.latemailsend.data.MailComposer
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var prefRepository: PrefRepository

    private lateinit var mMailComposer: MailComposer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        mMailComposer = MailComposer(prefRepository)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSendLateMail.setOnClickListener {
            sendLateEmail()
        }
        btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }

    }

    private fun sendLateEmail() {
        startActivity(mMailComposer.getLateMailIntent())
    }


}
