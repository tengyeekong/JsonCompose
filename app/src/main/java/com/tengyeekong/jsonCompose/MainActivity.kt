package com.tengyeekong.jsonCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tengyeekong.jsonCompose.ui.theme.JsonComposeTheme
import com.tengyeekong.jsonCompose.ui.dataclasses.UiBox
import com.tengyeekong.jsonCompose.ui.dataclasses.UiScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val screenData = UiScreen(
        component = "ui_box",
        title = "My UI Component",
        label = "Enter your data:",
        box = UiBox(
            width = 300,
            height = 200,
            color = "#EFEFEF"
        )
    )

    when (screenData.component) {
        Components.UI_BOX.value -> {
            BoxUI(screenData)
        }

        Components.UI_ROW.value -> {
            RowUI(screenData)
        }
    }
}

@Composable
fun BoxUI(screenData: UiScreen) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .border(width = 2.dp, color = Color.Black)
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        if (!screenData.title.isNullOrBlank()) {
            Text(
                text = screenData.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (!screenData.label.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .background(Color.LightGray)
                    .padding(12.dp)
            ) {
                Text(
                    text = screenData.label,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (screenData.box != null) {
            var boxModifier = Modifier.size(screenData.box.width.dp, screenData.box.height.dp)
            if (!screenData.box.color.isNullOrBlank()) {
                boxModifier = boxModifier.background(Color(android.graphics.Color.parseColor(screenData.box.color)))
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = boxModifier
            ) {
                Text(text = "Box")
            }
        }
    }
}

@Composable
fun RowUI(screenData: UiScreen) {
    Row {}
}

enum class Components(val value: String) {
    UI_BOX("ui_box"),
    UI_ROW("ui_row")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JsonComposeTheme {
        MainScreen()
    }
}