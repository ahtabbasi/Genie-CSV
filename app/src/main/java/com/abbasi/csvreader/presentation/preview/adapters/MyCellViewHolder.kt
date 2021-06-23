package com.abbasi.csvreader.presentation.preview.adapters

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.abbasi.csvreader.R
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder


/**
 * This is sample CellViewHolder class
 * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
 */
class MyCellViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    val cellContainer: LinearLayout = itemView.findViewById(R.id.cell_container)
    val cellTextview: TextView = itemView.findViewById(R.id.cell_data)
}