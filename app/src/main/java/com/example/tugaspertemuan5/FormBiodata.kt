package com.example.tugaspertemuan5

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit

@Composable
fun spResource(@DimenRes id: Int): TextUnit {
    val px = LocalContext.current.resources.getDimension(id)
    return with(LocalDensity.current) { px.toSp() }
}