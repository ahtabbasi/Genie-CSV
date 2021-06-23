package com.abbasi.csvreader.commons.mappers

import com.abbasi.csvreader.commons.fakeCSVData
import com.google.common.truth.Truth
import org.junit.Test

class CHMappersTest {

    @Test
    fun `test empty`() {

        val result = CHMappers.listToCHs(listOf())
        Truth.assertThat(result).isEmpty()
    }

    @Test
    fun `test fake data`() {

        val result = CHMappers.listToCHs(fakeCSVData.first())

        result.forEachIndexed { i, cell ->
            Truth.assertThat(cell.data).isEqualTo(fakeCSVData[0][i])
        }
    }
}