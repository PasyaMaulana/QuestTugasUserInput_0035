package com.example.tugaspertemuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tugaspertemuan5.ui.theme.TugasPertemuan5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasPertemuan5Theme {
                FormBiodataScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormBiodataScreenPreview() {
    TugasPertemuan5Theme {
        FormBiodataScreen()
    }
}