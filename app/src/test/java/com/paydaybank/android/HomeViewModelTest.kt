package com.paydaybank.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paydaybank.android.features.home.HomeViewModel
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.user.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository:UserRepository

    @Mock
    lateinit var transactionRepository: TransactionRepository

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(userRepository, transactionRepository)
    }

    @Test
    fun `Month change is reflected to the UI state`() {
        val month = "201902"
        homeViewModel.changeMonth(month)
        assertEquals(homeViewModel.state.value?.selectedMonth, month)
    }

}