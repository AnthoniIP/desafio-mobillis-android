package com.ipsoft.mobillis.di

import com.ipsoft.mobillis.repository.room.ItemDatabase
import com.ipsoft.mobillis.repository.room.RoomRepository
import com.ipsoft.mobillis.repository.sqlite.SQLiteRepository
import com.ipsoft.mobillis.ui.form.ItemFormViewModel
import com.ipsoft.mobillis.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

val androidModule = module {

    single { this }
    single { SQLiteRepository(ctx = get()) }
    single { RoomRepository(database = get()) }
    single { ItemDatabase.getDatabase(context = get()) }
    viewModel {
        MainActivityViewModel(repository = get())
    }
    viewModel {
        ItemFormViewModel(repository = get())
    }


}