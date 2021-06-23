package com.abbasi.csvreader.commons

import com.abbasi.geniecsv.ParserConfig

val fakeConfig = ParserConfig.build()

val fakeCSVText = "A,B,C" + fakeConfig.lineBreakCharacter +
        "1,2,3" + fakeConfig.lineBreakCharacter +
        "4,5,6"

val fakeCSVData = listOf(
    listOf("A", "B", "C"),
    listOf("1", "2", "3"),
    listOf("4", "5", "6")
)