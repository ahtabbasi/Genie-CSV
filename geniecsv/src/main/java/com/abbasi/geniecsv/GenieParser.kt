package com.abbasi.geniecsv


class GenieParser(override val config: ParserConfig) : Parser {

    override fun parseCsv(data: String): List<List<String>> {

        val rows = data.split(config.lineBreakCharacter)
        val result = mutableListOf<List<String>>()
        rows.forEach {
            result.add(parseCsvRow(it))
        }

        return manageMissMatchRow(manageEmptyRows(result))
    }

    override fun parseCsvRow(data: String): List<String> {

        if (data.isEmpty()) return listOf()

        var skipCount = 0L
        val manager = GenieRowManager(config)

        data.zipWithNext { ch, nextCh ->

            if (skipCount == 0L) {
                skipCount = manager.parseCharPair(ch, nextCh)
            } else {
                skipCount--
            }
        }

        // now parse the remaining last character..
        if (skipCount == 0L) { // this can occur if last two chars are quotes
            skipCount = manager.parseCharPair(data.last(), null)
        }

        val fields = manager.getFields()
        return manageTrailingSpaces(fields)
    }


    /**
     * Removes any empty rows based on config
     */
    private fun manageEmptyRows(data: MutableList<List<String>>): MutableList<List<String>> { //TODO: Test this method
        if (!config.skipEmptyLines) return data

        val result = mutableListOf<List<String>>()
        data.forEach { if (it.isNotEmpty()) result.add(it) }
        return result
    }

    /**
     * Removes any miss matching rows
     * (i.e. rows with more or less number of fields than first row) based on config
     */
    private fun manageMissMatchRow(data: MutableList<List<String>>): List<List<String>> { //TODO: Test this method
        if (!config.skipMissMatchRow || data.isEmpty()) return data

        val numOfFieldsInHeader = data.first().size
        val result = mutableListOf<List<String>>()
        data.forEach { if (it.size == numOfFieldsInHeader) result.add(it) }
        return result
    }

    /**
     * Removes any trailing spaces based on config
     */
    private fun manageTrailingSpaces(data: List<String>): List<String> { //TODO: Test this method
        if (!config.removeTrailingSpaces) return data

        return data.map { field ->
            field.filter {
                !it.isWhitespace()
            }
        }
    }


}