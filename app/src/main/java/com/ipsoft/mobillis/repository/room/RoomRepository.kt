package com.ipsoft.mobillis.repository.room

import androidx.lifecycle.LiveData
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.repository.ItemRepository

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class RoomRepository(database: ItemDatabase) : ItemRepository {

    private val itemDao = database.itemDao()
    override fun save(financialItem: FinancialItem) {
        if (financialItem.id == 0L) {
            val id = itemDao.insert(financialItem)
            financialItem.id = id
        } else {
            itemDao.update(financialItem)
        }
    }

    override fun remove(vararg financialItems: FinancialItem) {
        itemDao.delete(*financialItems)
    }

    override fun itemById(id: Long): LiveData<FinancialItem> {
        return itemDao.itemById(id)
    }
}