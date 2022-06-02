package br.com.luishenrique.alarmedevoz.presenter.fragment

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.service.alarmmanager.AlarmManager
import br.com.luishenrique.alarmedevoz.presenter.adapter.ColorAdapterListener
import br.com.luishenrique.alarmedevoz.presenter.adapter.ColorListAdapter
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.AlarmCommand
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.ICreateAlarmViewModel
import kotlinx.android.synthetic.main.fragment_create_alarm.*
import org.koin.android.ext.android.inject

class CreateAlarmFragment(
    private val onHideCreateAlarm: () -> Unit
) : Fragment(R.layout.fragment_create_alarm), ColorAdapterListener {

    private val viewModel by inject<ICreateAlarmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observable()
        setupView()
    }

    private fun setupView() {
        timePicker.setIs24HourView(true)

        with(listColors) {
            adapter = ColorListAdapter(requireActivity(), this@CreateAlarmFragment)
        }

        selectMelody.setOnClickListener {
            sendIntentMusic()
        }
        toolbarCancel.setOnClickListener {
            goToHome()
        }
        toolbarSave.setOnClickListener {
            saveAlarm()
        }
        timePicker.setOnTimeChangedListener { picker, _, _ ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewModel.setDate(picker.hour, picker.minute)
            }
        }
    }

    private fun observable() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is AlarmCommand.CreatedNewAlarm -> {
                    AlarmManager.create(requireContext(), it.value)
                    goToHome()
                }
                is AlarmCommand.MissingRepeatDay -> {

                }
                is AlarmCommand.MissingTheSound -> {

                }
                is AlarmCommand.MissingTheTitle -> {

                }
                is AlarmCommand.MissingTime -> {

                }
                is AlarmCommand.GenericError -> {

                }
                else -> {}
            }
        }
    }

    private fun saveAlarm() {
        viewModel.setTitle(editTitle.text.toString())
        viewModel.saveAlarm()
    }

    private fun goToHome() {
        with(parentFragmentManager.beginTransaction()) {
            remove(this@CreateAlarmFragment)
            onHideCreateAlarm.invoke()
            commit()
        }
    }

    private fun setColor(color: Int) {
        viewModel.setColor(color)
    }

    private fun sendIntentMusic() {
        val intent = Intent()
        intent.type = "audio/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data?.path ?: ""
            viewModel.setSound(uri)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(color: Int) {
        setColor(color)
    }
}