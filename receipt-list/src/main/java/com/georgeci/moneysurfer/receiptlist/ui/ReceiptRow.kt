package com.georgeci.moneysurfer.receiptlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgeci.moneysurfer.domain.entity.Receipt
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ReceiptRow(
    modifier: Modifier = Modifier,
    item: Receipt,
    onClick: (Receipt) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth().preferredHeight(88.dp),
        elevation = 16.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(
                onClick = { onClick(item) },
                indication = rememberRipple()
            ).padding(horizontal = 8.dp),
        ) {
            BasicText(text = "Counter: ${item.id} : ${item.summ}")
        }
    }
}


@Preview
@Composable
fun Preview() {
    ReceiptRow(
        item = Receipt(
            id = 100,
            summ = 1000.0,
            accountId = 500
        )
    ) {}
}