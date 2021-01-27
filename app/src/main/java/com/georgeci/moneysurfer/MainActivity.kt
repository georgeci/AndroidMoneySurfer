package com.georgeci.moneysurfer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.georgeci.moneysurfer.receiptedit.ui.ReceiptEditScreen
import com.georgeci.moneysurfer.receiptlist.ui.ReceiptListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            NavHost(navController, startDestination = "list") {
                composable("list") {
                    ReceiptListScreen()
                }
                composable("edit") {
                    ReceiptEditScreen(viewModel())
                }
            }
        }
    }

//    override fun onBackPressed() {
//        navController.popBackStack()
//    }
}