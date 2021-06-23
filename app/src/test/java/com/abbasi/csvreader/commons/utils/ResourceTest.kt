package com.abbasi.csvreader.commons.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ResourceTest {

    @Test
    fun `resource is valid when transformed`() {
        val input = Resource.Valid("test")
        val output = input.transform { "test" + "test" }
        assertThat((output as Resource.Valid).data).isEqualTo("testtest")
    }

    @Test
    fun `resource is invalid when transformed`() {
        val input = Resource.Invalid("message", "test")
        val output = input.transform { "test" + "test" }
        assertThat((output as Resource.Invalid).data).isEqualTo("testtest")
    }

    @Test
    fun `resource is loading when transformed`() {
        val input = Resource.Loading("test")
        val output = input.transform { "test" + "test" }
        assertThat((output as Resource.Loading).data).isEqualTo("testtest")
    }

    @Test
    fun `resource is valid when get data or null`() {
        val input = Resource.Valid("test")
        assertThat(input.getDataOrNull()).isEqualTo("test")
    }

    @Test
    fun `resource is invalid when get data or null`() {
        val input = Resource.Invalid("message", "test")
        assertThat(input.getDataOrNull()).isEqualTo("test")

    }

    @Test
    fun `resource is loading when get data or null`() {
        val input = Resource.Loading("test")
        assertThat(input.getDataOrNull()).isEqualTo("test")
    }
}