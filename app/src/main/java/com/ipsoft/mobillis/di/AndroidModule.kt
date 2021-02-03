package com.ipsoft.mobillis.di

import android.content.Context
import com.ipsoft.mobillis.repository.sqlite.SQLiteRepository
import com.ipsoft.mobillis.ui.form.FormItemViewModel
import com.ipsoft.mobillis.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

val androidModule = module {

    single { this }
    single { SQLiteRepository(get()) }
    viewModel {
        MainActivityViewModel(repository = get())
    }
    viewModel {
        FormItemViewModel()
    }

    factory {
        val context = get() as Context
    }

}