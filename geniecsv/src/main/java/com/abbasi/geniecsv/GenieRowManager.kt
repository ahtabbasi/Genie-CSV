package com.abbasi.geniecsv

internal class GenieRowManager(
    override val config: ParserConfig
) : RowParserStateManager {

    private val fields = mutableListOf<String>()
    private var field = ""
    private var quoteMode = false


    override fun parseCharPair(ch: Char, nextCh: Char?): Long {
        return when (ch) {
            config.separatorCharacter -> {
                if (quoteMode) addToField(ch)
                else completeField()
                0
            }

            config.quoteCharacter -> {
                // if two quote characters, that means it's actual char
                if (ch == nextCh) {
                    addToField(ch)
                    1 // need to skip the next char
                } else {
                    quoteMode = !quoteMode
                    0
                }
            }

            else -> {
                addToField(ch)
                0
            }
        }
    }

    override fun getFields(): List<String> {
        // duplicating to avoid changes in original
        // Adding the last parsed field data in the list
        return fields.toMutableList() + field
    }


    private fun addToField(ch: Char) {
        field += ch
    }

    private fun completeField() {
        fields.add(field)
        field = ""
    }
}