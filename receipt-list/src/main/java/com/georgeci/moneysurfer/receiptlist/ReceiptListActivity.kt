package com.georgeci.moneysurfer.receiptlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.georgeci.moneysurfer.receiptlist.arch.ReceiptListViewModel
import com.georgeci.moneysurfer.receiptlist.ui.ReceiptListScreen
import com.georgeci.moneysurfer.theme.MoneySurferTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class ReceiptListActivity : AppCompatActivity() {

    private val receiptListViewModel by viewModels<ReceiptListViewModel>()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneySurferTheme {
                ReceiptListScreen(receiptListViewModel = receiptListViewModel)
            }
        }
    }
}