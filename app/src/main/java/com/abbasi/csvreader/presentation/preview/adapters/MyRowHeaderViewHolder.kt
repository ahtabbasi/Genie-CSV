package com.abbasi.csvreader.presentation.preview.adapters

import android.view.View
import android.widget.TextView
import com.abbasi.csvreader.R
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder

/**
 * This is sample RowHeaderViewHolder class.
 * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
 */
internal class MyRowHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    val cellTextview: TextView = itemView.findViewById(R.id.cell_data)
}