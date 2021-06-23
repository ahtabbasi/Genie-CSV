package com.abbasi.geniecsv

import com.abbasi.geniecsv.utils.SameLineBreakOrSeparatorOrQuoteCharException
import java.nio.charset.Charset


class ParserConfig private constructor(
    val lineBreakCharacter: Char,
    val separatorCharacter: Char,
    val quoteCharacter: Char,
    val skipEmptyLines: Boolean,
    val skipMissMatchRow: Boolean,
    val removeTrailingSpaces: Boolean
) {
    companion object {

        fun build(
            lineBreakCharacter: Char = '\n',
            separatorCharacter: Char = ',',
            quoteCharacter: Char = '"',
            skipEmptyLines: Boolean = false,
            skipMissMatchRow: Boolean = false,
            removeTrailingSpaces: Boolean = false
        ): ParserConfig {

            if (lineBreakCharacter == separatorCharacter
                || lineBreakCharacter == quoteCharacter
                || separatorCharacter == quoteCharacter
            )
                throw SameLineBreakOrSeparatorOrQuoteCharException() //TODO: Test this
            else
                return ParserConfig(
                    lineBreakCharacter,
                    separatorCharacter,
                    quoteCharacter,
                    skipEmptyLines,
                    skipMissMatchRow,
                    removeTrailingSpaces
                )
        }
    }
}