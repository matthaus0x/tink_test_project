package br.com.matthaus.tinktest

import android.app.Application
import br.com.matthaus.tinktest.di.networkModule
import br.com.matthaus.tinktest.di.repositoryModule
import br.com.matthaus.tinktest.feature.doglist.di.dogListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TinkTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TinkTestApplication)
            modules(networkModule, repositoryModule, dogListModule)
        }
    }
}