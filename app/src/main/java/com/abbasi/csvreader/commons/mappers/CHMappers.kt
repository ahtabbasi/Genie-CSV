package com.abbasi.csvreader.commons.mappers

import com.abbasi.csvreader.presentation.models.ColumnHeader

/**
 * ColumnHeaderMappers
 */
object CHMappers {

    fun listToCHs(data: List<String>): MutableList<ColumnHeader> {
        return data.map {
            ColumnHeader(it)
        }.toMutableList()
    }
}