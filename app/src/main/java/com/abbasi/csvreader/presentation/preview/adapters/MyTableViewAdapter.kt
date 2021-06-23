package com.abbasi.csvreader.presentation.preview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.abbasi.csvreader.R
import com.abbasi.csvreader.presentation.models.Cell
import com.abbasi.csvreader.presentation.models.ColumnHeader
import com.abbasi.csvreader.presentation.models.RowHeader
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder


class MyTableViewAdapter : AbstractTableAdapter<ColumnHeader, RowHeader, Cell>() {

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_cell_layout, parent, false)
        return MyCellViewHolder(layout)
    }


    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cell: Cell?,
        columnPosition: Int,
        rowPosition: Int
    ) {

        // Get the holder to update cell item text
        val viewHolder = holder as MyCellViewHolder
        viewHolder.cellTextview.text = cell?.data ?: ""

        // It is necessary to remeasure itself.
        viewHolder.cellContainer.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        viewHolder.cellTextview.requestLayout()
    }


    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_column_header_layout, parent, false)
        return MyColumnHeaderViewHolder(layout)
    }


    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder,
        columnHeader: ColumnHeader?,
        position: Int
    ) {

        // Get the holder to update cell item text
        val columnHeaderViewHolder = holder as MyColumnHeaderViewHolder
        columnHeaderViewHolder.cellTextview.text = columnHeader?.data ?: ""

        // It is necessary to remeasure itself.
        columnHeaderViewHolder.columnHeaderContainer.layoutParams.width =
            LinearLayout.LayoutParams.WRAP_CONTENT
        columnHeaderViewHolder.cellTextview.requestLayout()
    }


    override fun onCreateRowHeaderViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_row_header_layout, parent, false)
        return MyRowHeaderViewHolder(layout)
    }


    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder,
        rowHeader: RowHeader?,
        position: Int
    ) {

        // Get the holder to update row header item text
        val rowHeaderViewHolder = holder as MyRowHeaderViewHolder
        rowHeaderViewHolder.cellTextview.text = rowHeader?.data ?: ""
    }


    override fun onCreateCornerView(parent: ViewGroup): View {
        // Get Corner xml layout
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.table_view_corner_layout, parent, false)
    }

}
