package com.example.test4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class ExampleUnitTest {
    val viewModel = ViewModel()

    @Test
    fun emailEmpty() {
        assert(
            viewModel.checkEmptyEmail("123@123.com")
        )
    }
    @Test
    fun passwordEmpty() {
        assert(
            viewModel.checkEmptyPassword("123")
        )
    }
    @Test
    fun emailPatternCheck() {
        assert(
            viewModel.checkEmptyEmail("123@123.com")
        )
    }
    @Test
    fun authSuccessCheck(){

        val viewModel = ViewModel()
        val result = viewModel.auth(
            email = "123@123.com",
            password = "123"
        )
        assert(
            result
        )
    }
    @Test
    fun authErrorCheck(){
        val viewModel = ViewModel()
        val result = viewModel.auth(
            email = "123@123123.com",
            password = "12324"
        )
        assertEquals(
            false, result
        )
    }
}