package br.com.matthaus.tinktest

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class TinkTestTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {  }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}