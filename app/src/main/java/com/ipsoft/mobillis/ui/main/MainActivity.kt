package com.ipsoft.mobillis.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.databinding.ActivityMainBinding
import com.ipsoft.mobillis.ui.details.ItemDetailsActivity
import com.ipsoft.mobillis.ui.form.ItemFormFragment
import com.ipsoft.mobillis.ui.list.ItemListFragment
import com.ipsoft.mobillis.ui.list.ItemListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    ItemListFragment.OnItemClickListener,
    MenuItem.OnActionExpandListener {

    private val viewModel: ItemListViewModel by viewModel()
    private val listFragment: ItemListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as ItemListFragment
    }
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.fabAdd.setOnClickListener {
            listFragment.hideDeleteMode()
            ItemFormFragment.newInstance().open(supportFragmentManager)

        }


    }


    override fun onItemClick(item: FinancialItem) {
        viewModel.itemIdSelected = item.id
        showDetailsActivity(item.id)
    }

    override fun onMenuItemActionExpand(item: MenuItem?) = true

    override fun onMenuItemActionCollapse(item: MenuItem?) = true

    private fun showDetailsActivity(itemId: Long) {
        ItemDetailsActivity.open(this, itemId)
    }

}