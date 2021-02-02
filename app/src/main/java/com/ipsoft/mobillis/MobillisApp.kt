package com.ipsoft.mobillis

import android.app.Application
import com.ipsoft.mobillis.di.androidModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.StandAloneContext.stopKoin

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

class MobillisApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(androidModule))
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}