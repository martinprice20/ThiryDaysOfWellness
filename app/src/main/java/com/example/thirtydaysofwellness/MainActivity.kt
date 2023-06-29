package com.example.thirtydaysofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirtydaysofwellness.model.WellnessRepository
import com.example.thirtydaysofwellness.ui.theme.ThirtyDaysOfWellnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysOfWellnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   WellnessTipApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessTipApp() {
    Scaffold(topBar = { WellnessTipAppBar() }) {
        val tips = WellnessRepository.wellnessTipList
        WellnessTipList(tipList = tips, Modifier.padding(it))
    }

}

@Composable
fun WellnessTipAppBar() {
    CenterAlignedTopAppBar(title = {
        Row {
            Text(
                text = stringResource(com.example.thirtydaysofwellness.R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        }
    })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirtyDaysOfWellnessTheme {
       WellnessTipApp()
    }
}