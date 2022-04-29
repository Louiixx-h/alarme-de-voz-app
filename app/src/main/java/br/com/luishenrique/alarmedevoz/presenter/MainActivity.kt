package br.com.luishenrique.alarmedevoz.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.presenter.fragment.CreateAlarmFragment
import br.com.luishenrique.alarmedevoz.presenter.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager.beginTransaction()) {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, HomeFragment())
            commit()
        }
    }
}