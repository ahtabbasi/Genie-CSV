package com.abbasi.csvreader.commons.mappers

import com.abbasi.csvreader.presentation.models.Cell

object CellMappers {

    fun listOfListsToCells(data: List<List<String>>): MutableList<MutableList<Cell>> {
        return data.map { list ->
            list.map {
                Cell(it)
            }.toMutableList()
        }.toMutableList()
    }
}