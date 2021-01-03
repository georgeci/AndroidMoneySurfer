package com.georgeci.moneysurfer.receiptlist.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgeci.moneysurfer.receiptlist.arch.ReceiptListIntent
import com.georgeci.moneysurfer.receiptlist.arch.ReceiptListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ReceiptListScreen(receiptListViewModel: ReceiptListViewModel) {
    val stateValue = receiptListViewModel.state().collectAsState().value
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bodyContent = {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(stateValue.items) { item ->
                    ReceiptRow(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                        item = item
                    ) {
                        receiptListViewModel.dispatch(
                            ReceiptListIntent.ReceiptClick(
                                it
                            )
                        )
                    }
                }
            }
        }, floatingActionButton = {
            FloatingActionButton(onClick = { receiptListViewModel.dispatch(ReceiptListIntent.Create) }) {
                Icon(imageVector = Icons.Default.Add, tint = Color.White)
            }
        })
}

@Preview
@Composable
fun ReceiptRowPreview() {
    ReceiptListScreen(ReceiptListViewModel())
}