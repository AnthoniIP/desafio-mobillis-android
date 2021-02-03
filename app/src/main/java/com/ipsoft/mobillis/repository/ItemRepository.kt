package com.ipsoft.mobillis.repository

import androidx.lifecycle.LiveData
import com.ipsoft.mobillis.data.model.FinancialItem

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

interface ItemRepository {

    fun save(financialItem: FinancialItem)
    fun remove(vararg financialItems: FinancialItem)
    fun itemById(id: Long) : LiveData<FinancialItem>


}