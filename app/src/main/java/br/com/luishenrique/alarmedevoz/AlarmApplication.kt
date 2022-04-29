package br.com.luishenrique.alarmedevoz

import android.app.Application
import br.com.luishenrique.alarmedevoz.data.config.HawkConfig
import br.com.luishenrique.alarmedevoz.di.setModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlarmApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        HawkConfig.init(this)

        startKoin {
            androidContext(this@AlarmApplication)
            setModule()
        }
    }
}