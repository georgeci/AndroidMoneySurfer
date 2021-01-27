package com.georgeci.moneysurfer.receiptedit.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.SavedStateHandle
import com.georgeci.moneysurfer.receiptedit.arch.ReceiptEditViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ReceiptEditScreen(receiptListViewModel: ReceiptEditViewModel = viewModel()) {
    val stateValue = receiptListViewModel.state().collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Receipt edit") }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        bodyContent = { Text("qwerty") }
    )
}

@Preview
@Composable
fun ReceiptEditPreview() {
    ReceiptEditScreen()
}