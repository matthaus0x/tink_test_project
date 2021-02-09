package br.com.matthaus.tinktest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TinkTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TinkTestTestApplication::class.java.name, context)
    }

}