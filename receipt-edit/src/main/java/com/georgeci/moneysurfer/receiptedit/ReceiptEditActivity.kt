package com.georgeci.moneysurfer.receiptedit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.georgeci.moneysurfer.theme.MoneySurferTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiptEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneySurferTheme {

            }
        }
    }
}