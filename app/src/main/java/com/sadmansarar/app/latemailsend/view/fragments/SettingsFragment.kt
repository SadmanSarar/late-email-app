package com.sadmansarar.app.latemailsend.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sadmansarar.app.latemailsend.R
import com.sadmansarar.app.latemailsend.data.repository.implimentation.PrefRepository
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var prefRepository: PrefRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etHrEmail.setText(prefRepository.getHrEmail())
        etMailBody.setText(prefRepository.getMailBody())
        etManagerEmail.setText(prefRepository.getManagerialEmail())
        etManagerialGreetings.setText(prefRepository.getManagerialGreetings())
        etYourName.setText(prefRepository.getYourName())

        btnSave.setOnClickListener {
            prefRepository.saveHrEmail(etHrEmail.text.toString())
            prefRepository.saveMailBody(etMailBody.text.toString())
            prefRepository.saveManagerialEmail(etManagerEmail.text.toString())
            prefRepository.saveManagerialGreetings(etManagerialGreetings.text.toString())
            prefRepository.saveYourName(etYourName.text.toString())
            Toast.makeText(requireContext(), "Settings saved", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SettingsFragment()
    }
}
