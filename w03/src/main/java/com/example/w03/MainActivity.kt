package com.example.w03

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.w03.ui.theme.WSU_202511030Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WSU_202511030Theme {
                HomeScreen()
            }
        }
    }
}

@Composable()
fun HomeScreen() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.wsurogo),
            contentDescription = "우송대 로고",
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .padding(10.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "디지털 시대!",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            "학생의 미래를 생각하는 대학!",
            style = MaterialTheme.typography.headlineMedium
        )
        Image(
            painter = painterResource(id = R.drawable.wsu),
            contentDescription = "우송대 건물",
            modifier = Modifier
        )
        Text(
            "<우송대학교>",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Button(
            onClick = {},
            modifier = Modifier.padding(top = 16.dp) // 버튼에 위쪽 여백 추가
        ) {
            Text("웹사이트로 이동")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}