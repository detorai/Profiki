package com.example.test4

import com.example.test4.presentation.sign_in.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class ExampleUnitTest {
    @Test
    fun emailEmpty() {
        val signInViewModel = SignInViewModel()
        assert(
            signInViewModel.checkEmptyEmail("123@123.com")
        )
    }
    @Test
    fun passwordEmpty() {
        val signInViewModel = SignInViewModel()
        assert(
            signInViewModel.checkEmptyPassword("123")
        )
    }
    @Test
    fun emailPatternCheck() {
        val signInViewModel = SignInViewModel()
        assert(
            signInViewModel.checkEmptyEmail("123@123.com")
        )
    }

}