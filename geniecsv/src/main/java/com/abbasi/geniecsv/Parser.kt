package com.abbasi.geniecsv

interface Parser {

    val config: ParserConfig

    fun parseCsv(data: String): List<List<String>>
    fun parseCsvRow(data: String): List<String>
}