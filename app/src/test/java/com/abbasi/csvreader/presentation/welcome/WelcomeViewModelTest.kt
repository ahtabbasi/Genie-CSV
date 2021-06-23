package com.abbasi.csvreader.presentation.welcome

import com.abbasi.csvreader.commons.CoroutineTestRule
import com.abbasi.csvreader.commons.ViewModelBaseTest
import com.abbasi.csvreader.commons.utils.Event
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WelcomeViewModelTest : ViewModelBaseTest() {

    private lateinit var viewModel: WelcomeViewModel

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()


    @Before
    fun init() {
        viewModel = WelcomeViewModel()
    }


    @Test
    fun `select file clicked event test`() =
        coroutineTestRule.testDispatcher.runBlockingTest {

            val list = mutableListOf<Event<Boolean>>()
            viewModel.selectFileClicked.observeForever { list.add(it) }

            viewModel.onSelectFileClicked()
            Truth.assertThat(list[0]).isInstanceOf(Event::class.java)
            Truth.assertThat(list[0].getContentIfNotHandled()).isEqualTo(true)
        }

}