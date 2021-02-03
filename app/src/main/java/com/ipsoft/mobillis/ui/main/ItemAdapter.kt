package com.ipsoft.mobillis.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.data.model.Type
import com.ipsoft.mobillis.databinding.FinantialItemBinding
import com.ipsoft.mobillis.util.CellClickListener

class ItemAdapter(
    private val list: List<FinancialItem>,
    private val cellClickListener: CellClickListener,
    ctx: Context
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val imageGreen = ctx.resources.getDrawable(R.drawable.ic_green_arrow)
    private val imageRed = ctx.resources.getDrawable(R.drawable.ic_red_arrow)
    private lateinit var itemBinding: FinantialItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding =
            FinantialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val rootView = itemBinding.root
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        var isDone = item.isDone

        holder.description.text = item.description
        holder.image.setImageDrawable(if (item.type == Type.RECEITA) imageGreen else imageRed)
        holder.isDone.visibility = if (isDone) VISIBLE else INVISIBLE
        holder.date.text = item.data.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item)
        }

    }

    override fun getItemCount() = list.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val root: View = itemBinding.itemCard
        val description: TextView = itemBinding.txtDescricao
        val value: TextView = itemBinding.txtValor
        val isDone: ImageView = itemBinding.checkConcluido
        val image: ImageView = itemBinding.itemIcon
        val date: TextView = itemBinding.txtData

    }


}
