package br.com.luishenrique.alarmedevoz

import android.app.Application
import br.com.luishenrique.alarmedevoz.di.setModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlarmApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AlarmApplication)
            setModule()
        }
    }
}