package com.abbasi.csvreader.domain

interface ParserRepository {

    suspend fun parseCsv(data: String): List<List<String>>
}