package com.abbasi.csvreader.data

import android.content.Context
import android.net.Uri
import com.abbasi.csvreader.commons.CoroutineTestRule
import com.abbasi.csvreader.commons.fakeCSVText
import com.abbasi.csvreader.commons.utils.Resource
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.io.ByteArrayInputStream

@ExperimentalCoroutinesApi
class LocalFileRepositoryTest {

    private lateinit var repository: LocalFileRepository

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val context = mockk<Context>(relaxed = true)


    @Before
    fun init() {
        repository = LocalFileRepository(
            coroutineTestRule.testDispatcher,
            this.context
        )
    }


    @Test
    fun `reading a valid file should return valid resource`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            // fake URI
            val fakeUriText = "fake_uri"
            val mockUri = Mockito.mock(Uri::class.java)
            mockkStatic(Uri::class)
            every { Uri.parse(fakeUriText) } returns mockUri

            // fake input
            every {
                this@LocalFileRepositoryTest.context.contentResolver.openInputStream(mockUri)
            } returns ByteArrayInputStream(fakeCSVText.toByteArray())


            val result = repository.readCsv(mockUri)

            Truth.assertThat(result).isInstanceOf(Resource.Valid::class.java)
            Truth.assertThat((result as Resource.Valid).data).isEqualTo(fakeCSVText)
        }


    @Test
    fun `reading an invalid file should return invalid resource`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            // fake URI
            val fakeUriText = "fake_uri"
            val mockUri = Mockito.mock(Uri::class.java)
            mockkStatic(Uri::class)
            every { Uri.parse(fakeUriText) } returns mockUri

            // fake input
            every {
                this@LocalFileRepositoryTest.context.contentResolver.openInputStream(mockUri)
            }.throws(Exception("Invalid data"))


            val result = repository.readCsv(mockUri)

            Truth.assertThat(result).isInstanceOf(Resource.Invalid::class.java)
        }

}