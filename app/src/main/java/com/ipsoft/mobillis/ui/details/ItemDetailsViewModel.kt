package com.ipsoft.mobillis.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.repository.ItemRepository

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class ItemDetailsViewModel(private val repository: ItemRepository) : ViewModel() {

    fun loadItemDetails(id: Long): LiveData<FinancialItem> {
        return repository.itemById(id)
    }
}