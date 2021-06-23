package com.abbasi.geniecsv

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class GenieParserCsvTest {

    private val config = ParserConfig.build()
    private lateinit var parser: GenieParser


    @Before
    fun setup() {
        parser = GenieParser(config)
    }


    @Test
    fun `parsing empty string should return empty data`() {
        // input:
        //
        val input = ""
        assertThat(parser.parseCsv(input)).isEqualTo(
            listOf(listOf<String>())
        )
    }


    @Test
    fun `parsing single line string should return single row`() {
        // input:
        // ABC, XYZ, 123
        val input = "ABC, XYZ, 123"
        assertThat(parser.parseCsv(input)).hasSize(1)
    }


    @Test
    fun `parsing 2 lines string should return 2 rows`() {
        // input:
        // ABC, XYZ, 123
        // DEF, JKL, 786
        val input = "ABC, XYZ, 123 ${config.lineBreakCharacter} DEF, JKL, 786"
        assertThat(parser.parseCsv(input)).hasSize(2)
    }


    @Test
    fun `parsing 2 lines string with first empty line should return 2 rows`() {
        // input:
        //
        // DEF, JKL, 786
        val input = "${config.lineBreakCharacter} DEF, JKL, 786"
        assertThat(parser.parseCsv(input)).hasSize(2)
    }


    @Test
    fun `parsing 2 lines string with last empty line should return 2 rows`() {
        // input:
        // ABC, XYZ, 123
        //
        val input = "ABC, XYZ, 123 ${config.lineBreakCharacter}"
        assertThat(parser.parseCsv(input)).hasSize(2)
    }


}
