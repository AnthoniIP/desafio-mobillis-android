package com.ipsoft.mobillis.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.data.model.Type
import com.ipsoft.mobillis.databinding.ItemFormBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class ItemFormFragment : DialogFragment() {

    private var _binding: ItemFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemFormViewModel by viewModel()
    private var item: FinancialItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = arguments?.getLong(EXTRA_ITEM_ID, 0) ?: -1
        if (itemId > 0) {
            viewModel.loadItem(itemId).observe(viewLifecycleOwner, Observer { item ->
                this.item = item
                showItem(item)
            })
        }
        dialog?.setTitle(R.string.action_new_item)
        // Abre o teclado virtual ao exibir o Dialog
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showItem(financialItem: FinancialItem) {
        binding.edtData.setText(financialItem.data.toString())
        binding.edtDescription.setText(financialItem.description)
        binding.edtValue.setText(financialItem.value.toString())

        if (financialItem.type == Type.RECEITA) binding.rdgType.check(R.id.rdb_receita) else
            binding.rdgType.check(R.id.rdb_despesa)
        binding.ckbDone.isChecked = financialItem.isDone


    }

    private fun errorInvalidItem() {

        Toast.makeText(requireContext(), R.string.error_invalid_item, Toast.LENGTH_SHORT).show()

    }

    private fun errorSaveItem() {
        Toast.makeText(requireContext(), R.string.error_item_not_found, Toast.LENGTH_SHORT).show()
    }

    private fun handleKeyboardEvent(actionId: Int): Boolean {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            saveItem()
            return true
        }
        return false
    }

    private fun saveItem() {
        val item = this.item ?: FinancialItem()
        val itemId = arguments?.getLong(EXTRA_ITEM_ID, 0) ?: 0
        item.id = itemId
        item.description = binding.edtDescription.text.toString()
        item.value = binding.edtValue.text.toString().toBigDecimal()
        item.isDone = binding.ckbDone.isChecked
        item.data = Date(binding.edtData.text.toString())

        try {
            if (viewModel.saveItem(item)) {
                dialog?.dismiss()
            } else {
                errorInvalidItem()
            }
        } catch (e: Exception) {
            errorSaveItem()

        }


    }

    fun open(fm: FragmentManager) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG)
        }
    }

    companion object {
        private const val DIALOG_TAG = "editDialog"
        const val EXTRA_ITEM_ID = "item"

        fun newInstance(itemId: Long = 0) = ItemFormFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_ITEM_ID, itemId)
            }
        }
    }
}