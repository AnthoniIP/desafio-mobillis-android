package com.ipsoft.mobillis.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.data.model.FinancialItem
import com.ipsoft.mobillis.data.model.Type
import com.ipsoft.mobillis.databinding.FinantialItemBinding

/**
 *
 *  Author:     Anthoni Ipiranga
 *  Project:    Mobillis
 *  Date:       03/02/2021
 */

class ItemAdapter(context: Context, items: List<FinancialItem>) :
    ArrayAdapter<FinancialItem>(context, 0, items) {

    private val imageGreen = context.resources.getDrawable(R.drawable.ic_green_arrow)
    private val imageRed = context.resources.getDrawable(R.drawable.ic_red_arrow)
    private lateinit var itemBinding: FinantialItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        var isDone = item?.isDone
        val viewHolder = if (convertView == null) {
            val view =
                FinantialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val rootView = itemBinding.root
            val holder = ViewHolder(rootView)
            rootView.tag = holder
            holder
        } else {
            convertView.tag as ViewHolder
        }

        if (item != null) {
            viewHolder.description.text = item.description
        }
        if (item != null) {
            viewHolder.image.setImageDrawable(if (item.type == Type.INCOME) imageGreen else imageRed)
        }
        viewHolder.isDone.visibility = if (isDone == true) View.VISIBLE else View.INVISIBLE
        if (item != null) {
            viewHolder.date.text = item.data.toString()
        }

        return viewHolder.view
    }

    inner class ViewHolder(val view: View) {
        val root: View = itemBinding.itemCard
        val description: TextView = itemBinding.txtDescricao
        val value: TextView = itemBinding.txtValor
        val isDone: ImageView = itemBinding.checkConcluido
        val image: ImageView = itemBinding.itemIcon
        val date: TextView = itemBinding.txtData
    }
}