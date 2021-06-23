package com.abbasi.csvreader.commons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
open class ViewModelBaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var lifecycleOwner: LifecycleOwner


    @Before
    fun setup() {
        // initializing MockK
        MockKAnnotations.init(this)

        val lifecycle = LifecycleRegistry(mockk())
        every { lifecycleOwner.lifecycle } returns (lifecycle)
        lifecycle.currentState = Lifecycle.State.RESUMED
    }
}