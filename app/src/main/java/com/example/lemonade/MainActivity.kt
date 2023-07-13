package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var step by remember {
        mutableStateOf(1)
    }
    var clickCount = when (step) {
        2 -> (2..5).random()
        else -> 1
    }
    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val textResource = when (step) {
        1 -> stringResource(R.string.tap_lemon_tree)
        2 -> stringResource(R.string.squeeze_lemon)
        3 -> stringResource(R.string.drink_lemonade)
        4 -> stringResource(R.string.start_again)
        else -> stringResource(R.string.tap_lemon_tree)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Lemonade",
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(16.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { step = if (clickCount == 1) step % 4 + 1 else {
                    clickCount--
                    2
                } },
                colors = ButtonDefaults.buttonColors(Color.Cyan),
                shape = RoundedCornerShape(24.dp),
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = textResource,
                )
            }
            Spacer(modifier = modifier.padding(16.dp))
            Text(
                text = textResource,
                fontSize = 18.sp,
                modifier = modifier
                    .padding(18.dp)
            )
        }

    }
}

