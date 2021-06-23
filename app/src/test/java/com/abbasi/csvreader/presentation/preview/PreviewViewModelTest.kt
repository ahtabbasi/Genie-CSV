package com.abbasi.csvreader.presentation.preview

import android.net.Uri
import com.abbasi.csvreader.commons.CoroutineTestRule
import com.abbasi.csvreader.commons.ViewModelBaseTest
import com.abbasi.csvreader.commons.fakeCSVData
import com.abbasi.csvreader.commons.fakeCSVText
import com.abbasi.csvreader.commons.utils.Resource
import com.abbasi.csvreader.domain.FileRepository
import com.abbasi.csvreader.domain.ParserRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class PreviewViewModelTest : ViewModelBaseTest() {

    private lateinit var viewModel: PreviewViewModel

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    lateinit var parserRepository: ParserRepository

    @RelaxedMockK
    lateinit var fileRepository: FileRepository

    private val fakeUriText = "fake_uri"
    private val mockUri = mock(Uri::class.java)


    @Before
    fun init() {
        viewModel = PreviewViewModel(
            coroutineTestRule.testDispatcher, parserRepository, fileRepository
        )

        mockkStatic(Uri::class)
        every { Uri.parse(fakeUriText) } returns mockUri
    }


    @After
    fun finish() {
        unmockkStatic(Uri::class)
    }


    @Test
    fun `first thing parse method should emit is loading`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val list = mutableListOf<Resource<List<List<String>>>>()
            viewModel.parsedData.observeForever { list.add(it) }

            viewModel.parse("")
            assertThat(list[0]).isInstanceOf(Resource.Loading::class.java)
        }


    @Test
    fun `if invalid uri string is passed, parse method should return invalid`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val fakeUriText = "invalid uri text"
            mockkStatic(Uri::class)
            every { Uri.parse(fakeUriText) } returns null


            val list = mutableListOf<Resource<List<List<String>>>>()
            viewModel.parsedData.observeForever { list.add(it) }
            viewModel.parse(fakeUriText)


            // should return loading
            assertThat(list[0]).isInstanceOf(Resource.Loading::class.java)

            // should return invalid
            assertThat(list[1]).isInstanceOf(Resource.Invalid::class.java)
        }

    @Test
    fun `if file repository returns invalid resource, parse method should return invalid`() =
        coroutineTestRule.testDispatcher.runBlockingTest {


            val errorMessage = "Error Message 123"
            coEvery { fileRepository.readCsv(mockUri) } returns Resource.Invalid(errorMessage)

            val list = mutableListOf<Resource<List<List<String>>>>()
            viewModel.parsedData.observeForever { list.add(it) }
            viewModel.parse(fakeUriText)


            // should return loading
            assertThat(list[0]).isInstanceOf(Resource.Loading::class.java)

            // should return invalid
            assertThat(list[1]).isInstanceOf(Resource.Invalid::class.java)

            // should have same message
            assertThat((list[1] as Resource.Invalid).message).isEqualTo(errorMessage)

        }


    @Test
    fun `if parse repository returns valid resource, parse method should return valid`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val fakeUriText = "fake_uri"
            val mockUri = mock(Uri::class.java)
            mockkStatic(Uri::class)
            every { Uri.parse(fakeUriText) } returns mockUri

            coEvery { fileRepository.readCsv(mockUri) } returns Resource.Valid(fakeCSVText)
            coEvery { parserRepository.parseCsv(fakeCSVText) } returns fakeCSVData

            val list = mutableListOf<Resource<List<List<String>>>>()
            viewModel.parsedData.observeForever { list.add(it) }
            viewModel.parse(fakeUriText)


            // should return loading
            assertThat(list[0]).isInstanceOf(Resource.Loading::class.java)

            // should return valid
            assertThat(list[1]).isInstanceOf(Resource.Valid::class.java)

            // should have same data
            assertThat((list[1] as Resource.Valid).data).isEqualTo(fakeCSVData)
        }


}