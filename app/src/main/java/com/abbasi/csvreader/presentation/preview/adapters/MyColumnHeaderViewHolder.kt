package com.abbasi.csvreader.presentation.preview.adapters

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.abbasi.csvreader.R
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder

/**
 * This is sample ColumnHeaderViewHolder class.
 * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
 */
internal class MyColumnHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    val columnHeaderContainer: LinearLayout = itemView.findViewById(R.id.column_header_container)
    val cellTextview: TextView = itemView.findViewById(R.id.column_header_textView)
}

