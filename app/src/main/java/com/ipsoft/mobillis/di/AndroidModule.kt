package com.ipsoft.mobillis.di

import com.ipsoft.mobillis.repository.ItemRepository
import com.ipsoft.mobillis.repository.room.ItemDatabase
import com.ipsoft.mobillis.repository.room.RoomRepository
import com.ipsoft.mobillis.ui.details.ItemDetailsViewModel
import com.ipsoft.mobillis.ui.form.ItemFormViewModel
import com.ipsoft.mobillis.ui.list.ItemListViewModel
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
    single { RoomRepository(ItemDatabase.getDatabase(context = get())) as ItemRepository }
    viewModel {
        ItemListViewModel(repository = get())
    }
    viewModel {
        ItemFormViewModel(repository = get())
    }
    viewModel {
        ItemDetailsViewModel(repository = get())
    }



}