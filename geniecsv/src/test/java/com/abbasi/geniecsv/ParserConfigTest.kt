package com.abbasi.geniecsv

import com.abbasi.geniecsv.utils.SameLineBreakOrSeparatorOrQuoteCharException
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ParserConfigTest {

    @Test
    fun `skipEmptyLines config on test`() {
        val config = ParserConfig.build(skipEmptyLines = true)
        val parser = GenieParser(config)

        val input = "ABC, XYZ, 123" + "${config.lineBreakCharacter}" +
                "" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123"

        assertThat(parser.parseCsv(input)).hasSize(2)
    }


    @Test
    fun `skipEmptyLines config off test`() {
        val config = ParserConfig.build(skipEmptyLines = false)
        val parser = GenieParser(config)

        val input = "ABC, XYZ, 123" + "${config.lineBreakCharacter}" +
                "" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123"

        assertThat(parser.parseCsv(input)).hasSize(3)
    }


    @Test
    fun `skipMissMatchRow config on test`() {
        val config = ParserConfig.build(skipMissMatchRow = true)
        val parser = GenieParser(config)

        val input = "ABC, XYZ, 123" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123, QWE" + "${config.lineBreakCharacter}" +
                "ABC, XYZ" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123"

        assertThat(parser.parseCsv(input)).hasSize(2)
    }


    @Test
    fun `skipMissMatchRow config off test`() {
        val config = ParserConfig.build(skipMissMatchRow = false)
        val parser = GenieParser(config)

        val input = "ABC, XYZ, 123" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123, QWE" + "${config.lineBreakCharacter}" +
                "ABC, XYZ" + "${config.lineBreakCharacter}" +
                "ABC, XYZ, 123"

        assertThat(parser.parseCsv(input)).hasSize(4)
    }


    @Test
    fun `removeTrailingSpaces config on test`() {
        val config = ParserConfig.build(removeTrailingSpaces = true)
        val parser = GenieParser(config)

        val input = " ABC , XYZ , 123 "

        assertThat(parser.parseCsv(input)).isEqualTo(
            listOf(
                listOf("ABC", "XYZ", "123")
            )
        )
    }


    @Test
    fun `removeTrailingSpaces config off test`() {
        val config = ParserConfig.build(removeTrailingSpaces = false)
        val parser = GenieParser(config)

        val input = " ABC , XYZ , 123 "

        assertThat(parser.parseCsv(input)).isEqualTo(
            listOf(
                listOf(" ABC ", " XYZ ", " 123 ")
            )
        )
    }


    @Test(expected = SameLineBreakOrSeparatorOrQuoteCharException::class)
    fun `passing same value for configurable characters should throw exception`() {
        ParserConfig.build(
            lineBreakCharacter = 'a',
            separatorCharacter = 'a',
            quoteCharacter = 'a'
        )
    }


}
