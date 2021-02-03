package com.ipsoft.mobillis.ui.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.ShareActionProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.databinding.FragmentItemDetailsBinding
import com.ipsoft.mobillis.ui.form.ItemFormFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding
    private val viewModel: ItemDetailsViewModel by viewModel()
    private var finantialItem: FinancialItem? = null
    private var shareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getLong(EXTRA_ITEM_ID, -1) ?: -1
        viewModel.loadItemDetails(id).observe(viewLifecycleOwner, Observer { item ->
            if (item != null) {
                showItemDetails(item)
            } else {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
                errorItemNotFound()

            }
        })
    }

    private fun showItemDetails(item: FinancialItem) {
        this.finantialItem = item


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.item_details, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.action_edit) {
            ItemFormFragment
                .newInstance(finantialItem?.id ?: 0)
                .open(requireFragmentManager())
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ITEM_ID = "item_id"
    }


}