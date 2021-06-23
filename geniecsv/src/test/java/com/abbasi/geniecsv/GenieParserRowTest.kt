package com.abbasi.geniecsv

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class GenieParserRowTest {

    private val config = ParserConfig.build()
    private lateinit var parser: GenieParser


    @Before
    fun setup() {
        parser = GenieParser(config)
    }


    @Test
    fun `parsing empty row should return empty list`() {
        assertThat(parser.parseCsvRow("")).isEmpty()
    }


    @Test
    fun `parsing row with 3 fields should return list with 3 fields`() {
        // input: ABC, XYZ, 123
        assertThat(parser.parseCsvRow("ABC, XYZ, 123")).isEqualTo(
            listOf("ABC", " XYZ", " 123")
        )
    }


    @Test
    fun `parsing a field inside quotes should return the field without quotes`() {
        // input: "ABC", XYZ, 123
        val input = "${config.quoteCharacter}ABC${config.quoteCharacter}, XYZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC", " XYZ", " 123")
        )
    }


    @Test
    fun `parsing a comma inside quotes should return the comma in the field`() {
        // input: "ABC, XYZ", 123
        val input = "${config.quoteCharacter}ABC, XYZ${config.quoteCharacter}, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC, XYZ", " 123")
        )
    }


    @Test
    fun `parsing a field with two adjacent quotes should return single quote in the field`() {
        // input: ABC"", XYZ, 123
        val input = "ABC${config.quoteCharacter}${config.quoteCharacter}, XYZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC${config.quoteCharacter}", " XYZ", " 123")
        )
    }


    @Test
    fun `parsing double quotes inside quotes should return single quote in the field`() {
        // input: "AB""C", XYZ, 123
        val input = "${config.quoteCharacter}AB${config.quoteCharacter}" +
                "${config.quoteCharacter}C${config.quoteCharacter}, XYZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("AB${config.quoteCharacter}C", " XYZ", " 123")
        )
    }


    @Test
    fun `when a string has a single quote, all data after it should be consider part of the field`() {
        // input: ABC, X"YZ, 123
        val input = "ABC, X${config.quoteCharacter}YZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC", " XYZ, 123")
        )
    }


    @Test
    fun `double quotes at the end of the field should return one quote`() {
        // input: ABC, XYZ, 123""
        val input = "ABC, XYZ, 123${config.quoteCharacter}${config.quoteCharacter}"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC", " XYZ", " 123${config.quoteCharacter}")
        )
    }


    @Test
    fun `when comma is the last character, last field will be empty`() {
        // input: ABC, XYZ, 123,
        val input = "ABC, XYZ, 123,"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("ABC", " XYZ", " 123", "")
        )
    }


    @Test
    fun `when comma is the first character, first field will be empty`() {
        // input: ,ABC, XYZ, 123
        val input = ",ABC, XYZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("", "ABC", " XYZ", " 123")
        )
    }


    @Test
    fun `when comma is the only character, both fields will be empty`() {
        // input: ,
        val input = ","
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("", "")
        )
    }


    @Test
    fun `parsing a field which only contains double quotes should return single quote in the field`() {
        // input: "", ABC, XYZ, 123
        val input = "${config.quoteCharacter}${config.quoteCharacter}, ABC, XYZ, 123"
        assertThat(parser.parseCsvRow(input)).isEqualTo(
            listOf("${config.quoteCharacter}", " ABC", " XYZ", " 123")
        )
    }


}