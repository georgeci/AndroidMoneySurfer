package com.georgeci.moneysurfer.receiptlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import com.georgeci.moneysurfer.receiptlist.ui.ReceiptListScreen
import com.georgeci.moneysurfer.theme.MoneySurferTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class ReceiptListActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            MoneySurferTheme {
                ReceiptListScreen(receiptListViewModel = viewModel())
            }
        }
    }
}