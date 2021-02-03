package com.ipsoft.mobillis.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.databinding.ActivityMainBinding
import com.ipsoft.mobillis.util.CellClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CellClickListener {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by viewModel()

    // Iniciando a RecyclerView
    var itemAdapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root
        setContentView(view)

        //initRecyclerView()

    }

    private fun initRecyclerView(list: List<FinancialItem>) {

        recyclerView = mainBinding.rvItems
        itemAdapter = ItemAdapter(list, this, this)
        recyclerView.adapter = itemAdapter
        recyclerView.setHasFixedSize(true)

        val llm = LinearLayoutManager(this)
        llm.isAutoMeasureEnabled = false
        recyclerView.layoutManager = llm


    }

    override fun onCellClickListener(data: FinancialItem) {
//        val intent = Intent(this, CheckingCopyAcitivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
//        intent.putExtra("id", data.id)
//        startActivity(intent)
    }

}