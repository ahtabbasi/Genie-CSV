package com.abbasi.csvreader.data

import com.abbasi.csvreader.commons.fakeCSVData
import com.abbasi.csvreader.commons.fakeCSVText
import com.abbasi.geniecsv.Parser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GenieParserRepositoryTest {

    private lateinit var repository: GenieParserRepository
    private val parser = mockk<Parser>(relaxed = true)


    @Before
    fun init() {
        repository = GenieParserRepository(parser)
    }


    @Test
    fun `reading a valid file should return valid resource`() = runBlockingTest {

        every { parser.parseCsv(any()) } returns fakeCSVData
        repository.parseCsv(fakeCSVText)
        verify { parser.parseCsv(fakeCSVText) }
    }
}

