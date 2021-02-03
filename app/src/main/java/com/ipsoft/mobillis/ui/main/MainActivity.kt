package com.ipsoft.mobillis.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.databinding.ActivityMainBinding
import com.ipsoft.mobillis.ui.form.ItemFormFragment
import com.ipsoft.mobillis.ui.list.ItemListFragment

class MainActivity : AppCompatActivity(),
ItemListFragment.OnItemClickListener,
MenuItem.OnActionExpandListener{

    private val listFragment: ItemListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentList) as ItemListFragment
    }
    private lateinit var mainBinding: ActivityMainBinding


    // Iniciando a RecyclerView
    private var itemAdapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root
        setContentView(view)

        mainBinding.fabAdd.setOnClickListener {
            listFragment.hideDeleteMode()
            ItemFormFragment.newInstance().open(supportFragmentManager)

        }


    }

    override fun onItemClick(item: FinancialItem) {
        TODO("Not yet implemented")
    }


}