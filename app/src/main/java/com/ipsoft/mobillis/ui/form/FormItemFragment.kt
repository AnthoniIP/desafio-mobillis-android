package com.ipsoft.mobillis.ui.form

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class FormItemFragment : DialogFragment(), NewItemView {

    private val viewModel: FormItemViewModel by viewModel()


    private var item: FinancialItem? = null

    override fun showItem(financialItem: FinancialItem) {
        TODO("Not yet implemented")
    }

    override fun errorInvalidItem() {

        Toast.makeText(requireContext(), R.string.error_invalid_item, Toast.LENGTH_SHORT).show()

    }

    override fun errorSaveItem() {
        Toast.makeText(requireContext(), R.string.error_item_not_found, Toast.LENGTH_SHORT).show()
    }
}