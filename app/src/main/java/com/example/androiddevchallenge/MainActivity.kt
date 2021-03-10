/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.TimerViewModel

class MainActivity : AppCompatActivity() {

    private val timerViewModel by viewModels<TimerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //timerViewModel.updateCountdown(30000L)
        setContent {
            MyTheme {
                MyApp(timerViewModel)
            }
        }
    }

}

// Start building your app here!
@Composable
fun MyApp(timerViewModel: TimerViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            CountDownInput(timerViewModel = timerViewModel)
            CountDown(timerViewModel = timerViewModel)
        }

    }
}

@Composable
fun CountDownInput(timerViewModel: TimerViewModel) {
    val (text, onTextChanged) = remember { mutableStateOf("") }
    val submit = {
        timerViewModel.updateCountdown(text.toLong())
    }
    Column {
        TextField(value = text, onValueChange = onTextChanged)
        TextButton(onClick = submit) {
            Text("Start")
        }
    }
}

@Composable
fun CountDown(timerViewModel: TimerViewModel) {
    val countDown = timerViewModel.countdown.observeAsState(0L)
    Text(text = countDown.value.toString(), modifier = Modifier.padding(8.dp))
}
