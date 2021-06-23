package com.abbasi.csvreader.data

import com.abbasi.csvreader.domain.ParserRepository
import com.abbasi.geniecsv.Parser
import javax.inject.Inject

class GenieParserRepository @Inject constructor(
    private val parser: Parser
) : ParserRepository {

    override suspend fun parseCsv(data: String) =
        parser.parseCsv(data)
}