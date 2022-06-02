package br.com.luishenrique.alarmedevoz.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import br.com.luishenrique.alarmedevoz.R
import br.com.luishenrique.alarmedevoz.data.repository.AlarmRepository
import br.com.luishenrique.alarmedevoz.data.repository.IAlarmRepository
import br.com.luishenrique.alarmedevoz.data.service.persistence.AlarmPersistence
import br.com.luishenrique.alarmedevoz.data.service.persistence.AlarmPersistenceProxy
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.CreateAlarmViewModel
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.HomeViewModel
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.ICreateAlarmViewModel
import br.com.luishenrique.alarmedevoz.presenter.viewmodel.IHomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val dataPersistenceModule = module {
    factory {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.file_name_shared_preferences),
            Context.MODE_PRIVATE
        )
    }
    factory { AlarmPersistence(get()) }
    factory { AlarmPersistenceProxy(get<AlarmPersistence>()) }
}

val repositoryModule = module {
    single<IAlarmRepository> {
        AlarmRepository(
            get<AlarmPersistenceProxy>(),
            MutableLiveData()
        )
    }
}

val viewModelModule = module {
    factory<ICreateAlarmViewModel> { CreateAlarmViewModel(get()) }
    factory<IHomeViewModel> { HomeViewModel(get()) }
}

fun setModule() {
    val modules = listOf(
        dataPersistenceModule,
        repositoryModule,
        viewModelModule,
    )
    loadKoinModules(modules)
}