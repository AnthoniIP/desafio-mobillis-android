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
    override fun save(item: FinancialItem) {
        if (item.id == 0L) {
            val id = itemDao.insert(item)
            item.id = id
        } else {
            itemDao.update(item)
        }
    }

    override fun update(item: FinancialItem) {
        itemDao.update(item)
    }

    override fun remove(vararg financialItems: FinancialItem) {
        itemDao.delete(*financialItems)
    }

    override fun itemById(id: Long): LiveData<FinancialItem> {
        return itemDao.itemById(id)
    }

    override fun getAllItem(): LiveData<List<FinancialItem>> {
        return itemDao.getAll()
    }
}