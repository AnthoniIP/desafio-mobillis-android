package com.ipsoft.mobillis.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class ItemListFragment : ListFragment(),
    AdapterView.OnItemLongClickListener,
    ActionMode.Callback {

    private val viewModel: ItemListViewModel by sharedViewModel()
    private var actionMode: ActionMode? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listView.onItemLongClickListener = this
        viewModel.showDetailsCommand().observe(viewLifecycleOwner, Observer { item ->
            if (item != null) {
                showItemDetails(item)
            }
        })
        viewModel.isInDeleteMode().observe(viewLifecycleOwner, Observer { deleteMode ->
            if (deleteMode == true) {
                showDeleteMode()
            } else {
                hideDeleteMode()
            }
        })
        viewModel.selectedHotels().observe(viewLifecycleOwner, Observer { hotels ->
            if (hotels != null) {
                showSelectedHotels(hotels)
            }
        })
        viewModel.selectionCount().observe(viewLifecycleOwner, Observer { count ->
            if (count != null) {
                updateSelectionCountText(count)
            }
        })
        viewModel.showDeletedMessage().observe(viewLifecycleOwner, Observer { count ->
            if (count != null && count > 0) {
                showMessageHotelsDeleted(count)
            }
        })
        viewModel.getItems()?.observe(viewLifecycleOwner, Observer { hotels ->
            if (hotels != null) {
                showHotels(hotels)
            }
        })


    }

    private fun showHotels(hotels: List<FinancialItem>) {
        val adapter = ItemAdapter(requireContext(), hotels)
        listAdapter = adapter
    }

    private fun showItemDetails(item: FinancialItem) {
        if (activity is OnItemClickListener) {
            val listener = activity as OnItemClickListener
            listener.onItemClick(item)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val item = l?.getItemAtPosition(position) as FinancialItem
        viewModel.selectItem(item)
    }


    override fun onItemLongClick(
        parent: AdapterView<*>?, view: View?,
        position: Int, id: Long
    ): Boolean {
        val consumed = (actionMode == null)
        if (consumed) {
            val item = parent?.getItemAtPosition(position) as FinancialItem
            viewModel.setInDeleteMode(true)
            viewModel.selectItem(item)
        }
        return consumed
    }

    private fun showDeleteMode() {
        val appCompatActivity = (activity as AppCompatActivity)
        actionMode = appCompatActivity.startSupportActionMode(this)
        listView.onItemLongClickListener = null
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
    }

     fun hideDeleteMode() {
        listView.onItemLongClickListener = this
        for (i in 0 until listView.count) {
            listView.setItemChecked(i, false)
        }
        listView.post {
            actionMode?.finish()
            listView.choiceMode = ListView.CHOICE_MODE_NONE
        }
    }

    private fun updateSelectionCountText(count: Int) {
        view?.post {
            actionMode?.title =
                resources.getQuantityString(R.plurals.list_item_selected, count, count)
        }
    }

    private fun showSelectedHotels(hotels: List<FinancialItem>) {
        listView.post {
            for (i in 0 until listView.count) {
                val item = listView.getItemAtPosition(i) as FinancialItem
                if (hotels.find { it.id == item.id } != null) {
                    listView.setItemChecked(i, true)
                }
            }
        }
    }

    private fun showMessageHotelsDeleted(count: Int) {
        Snackbar.make(
            listView,
            getString(R.string.message_items_deleted, count),
            Snackbar.LENGTH_LONG
        )
            .setAction(R.string.undo) {
                viewModel.undoDelete()
            }
            .show()
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_delete) {
            viewModel.deleteSelected()
            return true
        }
        return false
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        activity?.menuInflater?.inflate(R.menu.item_delete_list, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        viewModel.setInDeleteMode(false)
    }

    interface OnItemClickListener {
        fun onItemClick(item: FinancialItem)
    }


}