package com.abbasi.csvreader.commons.mappers

import com.abbasi.csvreader.commons.fakeCSVData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CellMappersTest {

    @Test
    fun `test empty`() {

        val result = CellMappers.listOfListsToCells(listOf())
        assertThat(result).isEmpty()
    }

    @Test
    fun `test fake data`() {

        val result = CellMappers.listOfListsToCells(fakeCSVData)

        result.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                assertThat(cell.data).isEqualTo(fakeCSVData[i][j])
            }
        }
    }

}