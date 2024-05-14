@file:OptIn(ExperimentalMaterial3Api::class)

package com.koiyae.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koiyae.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Limonada", fontWeight = FontWeight.Bold
                            )
                        }, colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color(0xFFEBEF73)
                        )
                    )
                }) { innerPadding ->
                    LemonadeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    when (currentStep) {
        1 -> {
            ImageAndText(textLabel = R.string.select,
                imageLabel = R.drawable.lemon_tree,
                contentDescriptionId = R.string.lemon_tree,
                atClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                })
        }

        2 -> {
            ImageAndText(textLabel = R.string.squeeze,
                imageLabel = R.drawable.lemon_squeeze,
                contentDescriptionId = R.string.lemon,
                atClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                })
        }

        3 -> {
            ImageAndText(textLabel = R.string.drink,
                imageLabel = R.drawable.lemon_drink,
                contentDescriptionId = R.string.glass_lemonade,
                atClick = {
                    currentStep = 4
                })
        }

        4 -> {
            ImageAndText(textLabel = R.string.start_again,
                imageLabel = R.drawable.lemon_restart,
                contentDescriptionId = R.string.empy_glass,
                atClick = {
                    currentStep = 1
                })
        }

    }
}


@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    textLabel: Int,
    imageLabel: Int,
    contentDescriptionId: Int,
    atClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = modifier
                .padding(
                    bottom = 20.dp
                ),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                onClick = atClick,
                shape = RoundedCornerShape(35.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2EDB5))
            ) {
                val painter = painterResource(imageLabel)
                Image(
                    painter = painter,
                    contentDescription = stringResource(contentDescriptionId)
                )
            }
        }
        Text(text = stringResource(textLabel))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeScreen()
    }
}
