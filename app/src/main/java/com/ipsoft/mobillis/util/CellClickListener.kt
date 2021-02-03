package com.ipsoft.mobillis.util

import com.ipsoft.mobillis.data.model.FinancialItem

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

interface CellClickListener {
    fun onCellClickListener(data: FinancialItem)
}