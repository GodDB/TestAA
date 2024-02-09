package com.example.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun MainScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "안드로이드 오토 테스트")
    }
}
