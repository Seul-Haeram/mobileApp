package com.example.w05

import com.example.w05.ui.theme.WSU_202511030Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WSU_202511030Theme {
                // TODO: 여기에 카운터와 스톱워치 UI를 만들도록 안내
                val count = remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CounterApp(count = count)
                    Spacer(modifier = Modifier.height(32.dp))
                    StopWatchApp()
                }
            }
        }
    }
}




@Composable
fun CounterApp(count: MutableState<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ) // TODO: 상태값 표시
        Row {
            Button(onClick = { count.value++ }) { Text("Increase") }
            Button(onClick = { count.value = 0 }) { Text("Reset") }
        }
    }
}


// 상위 컴포저블: 상태 소유 및 로직 처리 (Smart Component)
@Composable
fun StopWatchApp() {
    // 상태(State)와 로직(LaunchedEffect)은 상위 컴포저블에 둡니다.
    var timeInMillis by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var gameWon by remember { mutableStateOf(false) } // 11.11초 맞췄는지 여부

    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            while (!gameWon) {  // 게임이 끝날 때까지 스톱워치 계속 진행
                delay(10L) // 10ms마다 시간을 증가시킵니다.
                timeInMillis += 10L // 10ms씩 증가
                // 11.11초를 맞췄는지 확인
                if (timeInMillis == 11110L) {
                    gameWon = true
                    isRunning = false // 게임 끝
                }
            }
        }
    }

    // 게임을 진행하는 화면을 나타냅니다.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. 11.11초 맞추기 문구 추가
        Text(
            text = "11.11초 맞추기!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // 2. 스톱워치 시간 표시
        Text(
            text = formatTime(timeInMillis), // 시간을 MM:SS:ss 형식으로 표시
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        // 3. "축하합니다!" 메시지 추가 (게임 클리어 시)
        if (gameWon) {
            Text(
                text = "축하합니다!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // 4. 버튼들
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = { isRunning = true }) { Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { isRunning = false }) { Text("Stop") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                timeInMillis = 0L
                gameWon = false // 게임 초기화
                isRunning = false
            }) { Text("Reset") }
        }
    }
}

// 시간을 MM:SS:ss 형식으로 변환하는 헬퍼 함수
private fun formatTime(timeInMillis: Long): String {
    val minutes = (timeInMillis / 1000) / 60
    val seconds = (timeInMillis / 1000) % 60
    val millis = (timeInMillis % 1000) / 10 // 10ms 단위로 표시
    return String.format("%02d:%02d:%02d", minutes, seconds, millis)
}

// 하위 컴포저블: UI 표시 및 이벤트 전달 (Dumb/Stateless Component)
@Composable
fun StopwatchScreen(
    timeInMillis: Long, // 3. 상태를 직접 소유하지 않고 파라미터로 받습니다.
    onStartClick: () -> Unit, // 4. 이벤트가 발생했을 때 호출할 람다 함수를 받습니다.
    onStopClick: () -> Unit,
    onResetClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatTime(timeInMillis), // 전달받은 상태로 UI를 그립니다.
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            // 5. 버튼 클릭 시, 상태를 직접 변경하는 대신 전달받은 람다 함수를 호출합니다.
            Button(onClick = onStartClick) { Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) { Text("Stop") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onResetClick) { Text("Reset") }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    val count = remember { mutableStateOf(value = 0) }
    CounterApp(count = count)
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatchApp()
}
