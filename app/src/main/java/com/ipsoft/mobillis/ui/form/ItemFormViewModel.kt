package com.ipsoft.mobillis.ui.form

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

class ItemFormViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    fun loadItem(id: Long): LiveData<FinancialItem> {
        return repository.itemById(id)
    }

    fun saveItem(item: FinancialItem): Boolean {

        try {
            repository.save(item)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        return false
    }

}