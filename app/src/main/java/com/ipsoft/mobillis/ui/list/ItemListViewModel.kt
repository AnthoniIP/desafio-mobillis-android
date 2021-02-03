package com.ipsoft.mobillis.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.repository.ItemRepository
import com.ipsoft.mobillis.ui.common.SingleLiveEvent

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

class ItemListViewModel(private val repository: ItemRepository) : ViewModel() {

    val itemIdSelected: Long = -1
    private val items = repository.getAllItem()

    private val inDeleteMode = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val selectedItems = mutableListOf<FinancialItem>()
    private val selectionCount = MutableLiveData<Int>()
    private val selectedFinantialItems = MutableLiveData<List<FinancialItem>>().apply {
        value = selectedItems
    }
    private val deletedItems = mutableListOf<FinancialItem>()
    private val showDeletedMessage = SingleLiveEvent<Int>()
    private val showDetailsCommand = SingleLiveEvent<FinancialItem>()
    fun isInDeleteMode(): LiveData<Boolean> = inDeleteMode
    fun getItems(): LiveData<List<FinancialItem>>? = items

    fun selectionCount(): LiveData<Int> = selectionCount

    fun selectedHotels(): LiveData<List<FinancialItem>> = selectedFinantialItems

    fun showDeletedMessage(): LiveData<Int> = showDeletedMessage

    fun showDetailsCommand(): LiveData<FinancialItem> = showDetailsCommand

    fun selectItem(item: FinancialItem) {
        if (inDeleteMode.value == true) {
            toggleItemSelected(item)
            if (selectedItems.size == 0) {
                inDeleteMode.value = false
            } else {
                selectionCount.value = selectedItems.size
                selectedFinantialItems.value = selectedItems
            }

        } else {
            showDetailsCommand.value = item
        }

    }

    private fun toggleItemSelected(item: FinancialItem) {
        val existing = selectedItems.find { it.id == item.id }
        if (existing == null) {
            selectedItems.add(item)
        } else {
            selectedItems.removeAll { it.id == item.id }
        }

    }

    fun setInDeleteMode(deleteMode: Boolean) {
        if (!deleteMode) {
            selectionCount.value = 0
            selectedItems.clear()
            selectedFinantialItems.value = selectedItems
            showDeletedMessage.value = selectedItems.size
        }
        inDeleteMode.value = deleteMode
    }

    fun deleteSelected() {
        repository.remove(*selectedItems.toTypedArray())
        deletedItems.clear()
        deletedItems.addAll(selectedItems)
        setInDeleteMode(false)
        showDeletedMessage.value = deletedItems.size
    }

    fun undoDelete() {
        if (deletedItems.isNotEmpty()) {
            for (hotel in deletedItems) {
                hotel.id = 0L
                repository.save(hotel)
            }
        }
    }


}