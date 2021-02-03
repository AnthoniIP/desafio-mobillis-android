package com.ipsoft.mobillis.ui.form

import com.ipsoft.mobillis.data.model.FinancialItem

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

interface NewItemView {

    fun showHotel(financialItem: FinancialItem)
    fun errorInvalidItem()
    fun errorSaveItem()

}