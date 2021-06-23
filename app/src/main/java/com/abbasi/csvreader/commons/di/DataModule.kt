package com.abbasi.csvreader.commons.di

import android.content.Context
import com.abbasi.csvreader.data.GenieParserRepository
import com.abbasi.csvreader.data.LocalFileRepository
import com.abbasi.csvreader.domain.FileRepository
import com.abbasi.csvreader.domain.ParserRepository
import com.abbasi.geniecsv.GenieParser
import com.abbasi.geniecsv.Parser
import com.abbasi.geniecsv.ParserConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {

    @Provides
    @ActivityRetainedScoped
    fun provideFileRepository(
        dispatcher: CoroutineDispatcher,
        @ApplicationContext appContext: Context
    ): FileRepository {
        return LocalFileRepository(
            dispatcher,
            appContext
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideParserRepository(
        parser: Parser
    ): ParserRepository {
        return GenieParserRepository(parser)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideParser(
        config: ParserConfig
    ): Parser {
        return GenieParser(config)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideParserConfig(): ParserConfig {
        return ParserConfig.build(
            lineBreakCharacter = '\n',
            separatorCharacter = ',',
            quoteCharacter = '"',
            skipEmptyLines = false,
            skipMissMatchRow = false,
            removeTrailingSpaces = false
        )
    }

}