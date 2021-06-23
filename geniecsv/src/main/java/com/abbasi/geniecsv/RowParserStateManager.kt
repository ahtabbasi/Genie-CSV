package com.abbasi.geniecsv

internal interface RowParserStateManager {

    val config: ParserConfig

    /**
     * Parses the given character and the character afterwards
     * Returns the number of characters to skip in the next iteration
     *
     * Note: [nextCh] null means [ch] is the last character of the row
     */
    fun parseCharPair(ch: Char, nextCh: Char?): Long

    /**
     * Returns a list of all the parsed fields.
     */
    fun getFields(): List<String>
}