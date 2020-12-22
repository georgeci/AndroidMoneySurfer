package com.georgeci.moneysurfer.receiptlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.georgeci.moneysurfer.receiptlist.arch.ReceiptListIntent
import com.georgeci.moneysurfer.receiptlist.arch.ReceiptListViewModel
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
                Surface(color = MaterialTheme.colors.background) {
                    val stateValue = receiptListViewModel.state().value
                    Column() {
                        ClickCounter(stateValue.i) {
                            receiptListViewModel.dispatchIntent(ReceiptListIntent.Plus)
                        }
                        LazyColumn {
                            items(stateValue.items) { item ->
                                ClickCounter(clicks = item.state().value){
                                    item.dispatchIntent(Unit)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        BasicText("I've been clicked $clicks times")
    }
}