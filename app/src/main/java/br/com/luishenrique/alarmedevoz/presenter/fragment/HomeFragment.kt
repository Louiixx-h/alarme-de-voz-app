package br.com.luishenrique.alarmedevoz.presenter.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.entity.Alarm
import br.com.luishenrique.alarmedevoz.presenter.adapter.AlarmAdapterListener
import br.com.luishenrique.alarmedevoz.presenter.adapter.AlarmListAdapter
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.HomeAlarmState
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.HomeViewModel
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.IHomeViewModel
import br.com.luishenrique.alarmedevoz.utils.toast
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home), AlarmAdapterListener {

    private val adapter by lazy { AlarmListAdapter(this) }
    private val viewModel by inject<IHomeViewModel>()
    private val onHideCreateAlarm: () -> Unit = { showFab() }

    companion object {
        const val NAME = "HOME_FRAGMENT"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAlarms()
    }

    private fun setupObservables() {
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is HomeAlarmState.Success -> {
                    setupView(it.value)
                }
                is HomeAlarmState.GenericError -> {
                    showError()
                }
            }
        }
    }

    private fun setupListeners() {
        fabCreateAlarm.setOnClickListener {
            hideFab()
            goToCreateAlarm()
        }
    }

    private fun setupView(alarms: List<Alarm>) {
        rvAlarms.adapter = adapter.apply {
            this.mItems = alarms as MutableList<Alarm>
        }
    }

    private fun showError() {
        toast(getString(R.string.error_message))
    }

    private fun goToCreateAlarm() {
        with(parentFragmentManager.beginTransaction()) {
            setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_out)
            add(R.id.fragment_container_view, CreateAlarmFragment(onHideCreateAlarm))
            commit()
        }
    }

    private fun hideFab() {
        fabCreateAlarm.visibility = View.GONE
    }

    private fun showFab() {
        fabCreateAlarm.visibility = View.VISIBLE
    }

    override fun onClick(alarm: Alarm) {

    }
}