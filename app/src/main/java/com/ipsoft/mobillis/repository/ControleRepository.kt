package com.ipsoft.mobillis.repository

import com.ipsoft.mobillis.data.model.FinancialItem

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       02/02/2021
 */

interface ControleRepository {

    fun save(financialItem: FinancialItem)
    fun remove(vararg financialItems: FinancialItem)
    fun itemById(id: Long, callback: (FinancialItem?) -> Unit)


}